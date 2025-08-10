package com.pet.moduleregister.application.moduleClassStudent.services.usecases;

import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application._shared.exceptions.NotRegistrationTimeException;
import com.pet.moduleregister.application.moduleClassStudent.dto.ClassSchedule;
import com.pet.moduleregister.application.moduleClassStudent.dto.ModuleClassDTO;
import com.pet.moduleregister.application.moduleClassStudent.dto.RegisteredModuleClass;
import com.pet.moduleregister.application.moduleClassStudent.dto.RegistrationPeriod;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.DuplicatedRegistrationException;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.NoAvailableSlotsException;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.ScheduleConflictsException;
import com.pet.moduleregister.application.moduleClassStudent.ports.in.usecases.RegisterModuleClass;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.GetCurrentPeriodPort;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.GetModuleClassPort;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.GetSchedulesOfClassPort;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.ModuleClassStudentRepositoryPort;
import com.pet.moduleregister.entities.moduleClassStudent.ModuleClassStudent;
import com.pet.moduleregister.entities.moduleClassStudent.enums.LearnStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RegisterModuleClassImpl implements RegisterModuleClass {

    private final RedisTemplate<String, String> redisTemplate;
    private final GetModuleClassPort getModuleClass;
    private final ModuleClassStudentRepositoryPort moduleClassStudentRepository;
    private final GetSchedulesOfClassPort getSchedulesOfClass;
    private final GetCurrentPeriodPort getCurrentPeriod;

    /*
    * NOTE: Although the method `registerModuleClass` already check if the student registered for this class before,
    * the Redis Lua script step that check the condition again is still needed to avoid race condition (at db)
    * when multiple requests try to register the same student for the same class at the same time.
    * */
    private static final String REGISTER_LUA = """
        if redis.call("SISMEMBER", KEYS[2], ARGV[1]) == 1 then
            return -1   -- Already registered
        end
        local slots = redis.call("GET", KEYS[1])
        if not slots then
            return -2   -- Available slots not set
        end
        slots = tonumber(slots)
        if slots > 0 then
            redis.call("DECR", KEYS[1])
            redis.call("SADD", KEYS[2], ARGV[1])
            return 1    -- Registration successful
        else
            return 0    -- Out of slots
        end
    """;

    private final String ROLLBACK_LUA = """
        redis.call("INCR", KEYS[1])
        redis.call("SREM", KEYS[2], ARGV[1])
        return 1
    """;

    @Override
    @Transactional
    public RegisteredModuleClass registerModuleClass(AuthUser currentStudent, String classCode) {
        // Check if the registration time is valid
        RegistrationPeriod period = getCurrentPeriod.getCurrentPeriod()
                .orElseThrow(() -> new NotRegistrationTimeException(
                        "No current registration period found. Please check the registration periods."
                ));
        // Check if the class code is valid
        ModuleClassDTO newModuleClass = getModuleClass.getModuleClassByCode(classCode)
                .orElseThrow(() -> new NotFoundException(
                        "Module class with code " + classCode + " does not exist."
                ));
        // Check if the class can be registered in the current period
        if (!period.getSemesterId().equals(newModuleClass.getSemesterId())) {
            throw new NotRegistrationTimeException("Registration is not allowed at this time.");
        }
        // Check if the student is already registered for the class
        List<ModuleClassStudent> currentClasses = moduleClassStudentRepository.findByStudentId(currentStudent.getUserId());
        if (!currentClasses.isEmpty()) {
            if (currentClasses.stream().anyMatch(c -> c.getModuleClassId().equals(newModuleClass.getModuleClassId()))) {
                throw new DuplicatedRegistrationException(
                        "Student " + currentStudent.getUserCode() + " is already registered for class " + newModuleClass.getModuleClassCode() + "."
                );
            }
            // Check for schedule conflicts with existing classes
            checkScheduleConflicts(currentClasses, newModuleClass);
        }

        // Check if the student can register for the class (is not registered and class still has available slots)
        checkValidRegistrationForClass(currentStudent.getUserCode(), classCode);

        // Register the student for the class (save to db)
        Instant now = Instant.now();
        try {
            ModuleClassStudent moduleClassStudent = new ModuleClassStudent();

            moduleClassStudent.setModuleClassId(newModuleClass.getModuleClassId());
            moduleClassStudent.setStudentId(currentStudent.getUserId());
            moduleClassStudent.setStatus(LearnStatus.REGISTERED);
            moduleClassStudent.setRetakeCount(0); // Retake count starts at 0
            moduleClassStudent.setCreatedAt(now);
            moduleClassStudent.setUpdatedAt(now);

            moduleClassStudentRepository.create(moduleClassStudent);
        } catch (Exception e) {
            // Rollback Redis changes if DB save fails
            rollBackRedisChange(currentStudent.getUserCode(), classCode);
            throw new RuntimeException("Failed to register student for class: " + e.getMessage(), e);
        }

        return new RegisteredModuleClass(newModuleClass.getModuleClassCode(), now);
    }

    private void checkScheduleConflicts(List<ModuleClassStudent> currentClasses, ModuleClassDTO newModuleClass) {
        // This method should check if the student has any schedule conflicts with the class
        Long[] currentClassIds = currentClasses.stream()
                .map(ModuleClassStudent::getModuleClassId)
                .toArray(Long[]::new);
        List<ClassSchedule> oldSchedules = getSchedulesOfClass.getSchedulesOfClass(currentClassIds);
        ClassSchedule newClassSchedule = getSchedulesOfClass.getSchedulesOfClass(newModuleClass.getModuleClassId())
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Class schedule for " + newModuleClass.getModuleClassCode() + " not found."));

        for (ClassSchedule schedule : oldSchedules) {
            if (isScheduleConflict(schedule, newClassSchedule)) {
                throw new ScheduleConflictsException(
                        "Schedule conflict detected for class " + newModuleClass.getModuleClassCode() +
                                " on day " + schedule.getDayOfWeek() + "."
                );
            }
        }
    }

    private boolean isScheduleConflict(ClassSchedule oldSchedule, ClassSchedule newSchedule) {
        if (oldSchedule.getModuleClassId().equals(newSchedule.getModuleClassId())) {
            return false;
        }
        return oldSchedule.getDayOfWeek() == newSchedule.getDayOfWeek() &&
                (oldSchedule.getStartPeriod() < newSchedule.getEndPeriod() &&
                        oldSchedule.getEndPeriod() > newSchedule.getStartPeriod());
    }

    private void checkValidRegistrationForClass(String studentCode, String classCode) {
        // Check if the class is already registered
        // Check available slots
        String slotKey = "class:slots:" + classCode;
        String studentSetKey = "class:students:" + classCode;

        // 1. Execute Lua Script check & reserve slot
        long result = redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.scriptingCommands().eval(
                        REGISTER_LUA.getBytes(),
                        ReturnType.INTEGER,
                        2,
                        slotKey.getBytes(),
                        studentSetKey.getBytes(),
                        studentCode.getBytes()
                )
        );

        if (result == -2L) {
            throw new IllegalStateException(
                    "Available slots for class " + classCode + " are not set."
            );
        } else if (result == -1L) {
            throw new DuplicatedRegistrationException(
                    "Student " + studentCode + " is already registered for class " + classCode + "."
            );
        } else if (result == 0L) {
            throw new NoAvailableSlotsException(
                    "No available slots for class " + classCode + "."
            );
        }
    }

    private void rollBackRedisChange(String studentCode, String classCode) {
        String slotKey = "class:slots:" + classCode;
        String studentSetKey = "class:students:" + classCode;

        redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.scriptingCommands().eval(
                        ROLLBACK_LUA.getBytes(),
                        ReturnType.INTEGER,
                        2,
                        slotKey.getBytes(),
                        studentSetKey.getBytes(),
                        studentCode.getBytes()
                )
        );
    }

    @Override
    public void registerModuleClasses(String[] classCode) {

    }
}
