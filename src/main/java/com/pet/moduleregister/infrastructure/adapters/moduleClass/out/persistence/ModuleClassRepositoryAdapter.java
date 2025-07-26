package com.pet.moduleregister.infrastructure.adapters.moduleClass.out.persistence;

import com.pet.moduleregister.application.moduleClass.dto.Lecturer;
import com.pet.moduleregister.application.moduleClass.dto.OpeningClass;
import com.pet.moduleregister.application.moduleClass.dto.Schedule;
import com.pet.moduleregister.infrastructure.adapters.moduleClass.out.persistence.mappers.ModuleClassDomainMapper;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.domain.moduleClass.ModuleClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ModuleClassRepositoryAdapter implements ModuleClassRepositoryPort {
    private final ModuleClassJpaRepository moduleClassJpaRepository;
    private final ModuleClassDomainMapper moduleClassDomainMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ModuleClass> findBySemesterId(Long semesterId) {
        return moduleClassJpaRepository.findBySemesterId(semesterId)
                .stream()
                .map(moduleClassDomainMapper::toDomain)
                .toList();
    }

    @Override
    public List<OpeningClass> findOpeningClassesBySemesterId(Long semesterId, int limit) {
        String jpql = """
                SELECT mc.moduleClassId as moduleClassId,
                    mc.moduleClassCode as moduleClassCode,
                    m.moduleName as moduleName,
                    m.numberOfCredits as numberOfCredits,
                    u.userId as lecturerId,
                    u.firstName as lecturerFirstName,
                    u.lastName as lecturerLastName,
                    mc.maxParticipants as maxParticipants,
                    COUNT (mcs.studentId) as currentParticipants
                FROM ModuleClassEntity mc
                JOIN ModuleEntity m ON mc.moduleId = m.moduleId
                JOIN UserEntity u ON mc.lecturerId = u.userId
                JOIN ModuleClassStudentEntity mcs ON mc.moduleClassId = mcs.moduleClassId
                WHERE mc.semesterId = :semesterId
                GROUP BY mc.moduleClassId, mc.moduleClassCode,
                    m.moduleName, m.numberOfCredits,
                    u.userId, u.firstName, u.lastName,
                    mc.maxParticipants
                """;

        TypedQuery<Tuple> query = entityManager.createQuery(jpql, Tuple.class);
        query.setParameter("semesterId", semesterId);
        query.setMaxResults(limit);

        List<Tuple> results = query.getResultList();

        List<Schedule> schedules = getSchedulesByModuleClassIds(
                results.stream()
                        .map(result -> result.get("moduleClassId", Long.class))
                        .toArray(Long[]::new)
        );
        Map<Long, List<Schedule>> schedulesMap = schedules.stream()
                .collect(Collectors.groupingBy(Schedule::getModuleClassId));

        return results.stream().map(
                result -> {
                    Long moduleClassId = result.get("moduleClassId", Long.class);
                    String moduleClassCode = result.get("moduleClassCode", String.class);
                    String moduleName = result.get("moduleName", String.class);
                    int numberOfCredits = result.get("numberOfCredits", Integer.class);
                    Long lecturerId = result.get("lecturerId", Long.class);
                    String lecturerFirstName = result.get("lecturerFirstName", String.class);
                    String lecturerLastName = result.get("lecturerLastName", String.class);
                    int maxParticipants = result.get("maxParticipants", Integer.class);
                    int currentParticipants = result.get("currentParticipants", Long.class).intValue();

                    Lecturer lecturer = new Lecturer(lecturerId, lecturerFirstName, lecturerLastName);
                    List<Schedule> schedulesL = schedulesMap.getOrDefault(moduleClassId, List.of());

                    return new OpeningClass(
                            moduleClassId,
                            moduleClassCode,
                            moduleName,
                            numberOfCredits,
                            lecturer,
                            schedulesL,
                            maxParticipants,
                            currentParticipants
                    );
                }
        ).toList(
        ) ;
    }

    private List<Schedule> getSchedulesByModuleClassIds(Long... moduleClassIds) {
        String jpql = """
                SELECT s.moduleClassId as moduleClassId,
                    s.dayOfWeek as dayOfWeek,
                    s.startPeriod as startPeriod,
                    s.endPeriod as endPeriod,
                    s.room as room
                FROM ClassScheduleEntity s
                WHERE s.moduleClassId IN :moduleClassIds
                """;

        TypedQuery<Tuple> query = entityManager.createQuery(jpql, Tuple.class);
        query.setParameter("moduleClassIds", Arrays.asList(moduleClassIds));

        return query.getResultList().stream().map(
                result -> new Schedule(
                        result.get("moduleClassId", Long.class),
                        result.get("dayOfWeek", DayOfWeek.class),
                        result.get("startPeriod", Integer.class),
                        result.get("endPeriod", Integer.class),
                        result.get("room", String.class)
                )
        ).toList();
    }
}
