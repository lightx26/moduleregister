package com.pet.moduleregister.application.moduleClassStudent.services;

import com.pet.moduleregister.application.moduleClassStudent.exceptions.DuplicateRegistrationException;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.NoAvailableSlotsException;
import com.pet.moduleregister.application.moduleClassStudent.ports.in.usecases.RegisterModuleClass;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.CheckModuleClassExistsPort;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class RegisterModuleClassImpl implements RegisterModuleClass {

    private final RedisTemplate<String, String> redisTemplate;
    private final CheckModuleClassExistsPort checkModuleClassExists;

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
    public void registerModuleClass(String studentCode, String classCode) {
        // Check if the class code is valid
        if (!checkModuleClassExists.existsByClassCode(classCode)) {
            throw new NotFoundException(
                    "Module class with code " + classCode + " does not exist."
            );
        }
        // Check the schedule for conflicts
        // Check if the student can register for the class (is not registered and class still has available slots)
        checkValidRegistrationForClass(studentCode, classCode);

        // Register the student for the class (save to db)
        try {

        } catch (Exception e) {
            // Rollback Redis changes if DB save fails
            redisTemplate.execute((RedisCallback<Long>) connection ->
                    connection.scriptingCommands().eval(
                            ROLLBACK_LUA.getBytes(),
                            ReturnType.INTEGER,
                            2,
                            ("class:slots:" + classCode).getBytes(),
                            ("class:students:" + classCode).getBytes(),
                            studentCode.getBytes()
                    )
            );
            throw new RuntimeException("Failed to register student for class: " + e.getMessage(), e);
        }
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
            throw new DuplicateRegistrationException(
                    "Student " + studentCode + " is already registered for class " + classCode + "."
            );
        } else if (result == 0L) {
            throw new NoAvailableSlotsException(
                    "No available slots for class " + classCode + "."
            );
        }
    }

    @Override
    public void registerModuleClasses(String[] classCode) {

    }
}
