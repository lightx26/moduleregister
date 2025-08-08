package com.pet.moduleregister.infrastructure.adapters.out.classSchedule.persistence;

import com.pet.moduleregister.application.classSchedule.dto.Schedule;
import com.pet.moduleregister.application.classSchedule.ports.out.ClassScheduleRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
@Repository
public class ClassScheduleRepositoryAdapter implements ClassScheduleRepositoryPort {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Schedule> getSchedulesByModuleClassIds(Long... moduleClassIds) {
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
