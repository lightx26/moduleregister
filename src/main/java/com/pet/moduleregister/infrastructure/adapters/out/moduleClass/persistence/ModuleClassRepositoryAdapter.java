package com.pet.moduleregister.infrastructure.adapters.out.moduleClass.persistence;

import com.pet.moduleregister.application.moduleClass.dto.ClassParticipant;
import com.pet.moduleregister.application.moduleClass.dto.Lecturer;
import com.pet.moduleregister.application.moduleClass.dto.OpeningClass;
import com.pet.moduleregister.application.moduleClass.dto.OpeningClassDetails;
import com.pet.moduleregister.entities.moduleClassStudent.enums.LearnStatus;
import com.pet.moduleregister.infrastructure.adapters.out.moduleClass.persistence.mappers.ModuleClassDomainMapper;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.entities.moduleClass.ModuleClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                LEFT JOIN ModuleClassStudentEntity mcs ON mc.moduleClassId = mcs.moduleClassId
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

                    return new OpeningClass(
                            moduleClassId,
                            moduleClassCode,
                            moduleName,
                            numberOfCredits,
                            lecturer,
                            List.of(),  // ,
                            maxParticipants,
                            currentParticipants
                    );
                }
        ).toList();
    }

    public Optional<OpeningClassDetails> findOpeningModuleClassByCode(String classCode) {
        List<LearnStatus> statuses = List.of(
                LearnStatus.REGISTERED,
                LearnStatus.WAITING
        );
        String jpql = """
                SELECT mc.moduleClassId as moduleClassId,
                    mc.moduleClassCode as moduleClassCode,
                    m.moduleName as moduleName,
                    m.numberOfCredits as numberOfCredits,
                    u.userId as lecturerId,
                    u.firstName as lecturerFirstName,
                    u.lastName as lecturerLastName,
                    mc.maxParticipants as maxParticipants,
                    mcs.studentId as studentId,
                    s.userCode as studentCode,
                    s.firstName as studentFirstName,
                    s.lastName as studentLastName,
                    sg.groupName as groupName,
                    p.programCode as programCode,
                    p.programName as programName,
                    mcs.status as status
                FROM ModuleClassEntity mc
                JOIN ModuleEntity m ON mc.moduleId = m.moduleId
                JOIN UserEntity u ON mc.lecturerId = u.userId
                LEFT JOIN ModuleClassStudentEntity mcs ON mc.moduleClassId = mcs.moduleClassId AND mcs.status IN :statuses
                LEFT JOIN UserEntity s ON mcs.studentId = s.userId
                LEFT JOIN StudentGroupEntity sg ON s.studentGroupId = sg.studentGroupId
                LEFT JOIN ProgramEntity p ON s.programId = p.programId
                WHERE mc.moduleClassCode = :classCode
                """;

        TypedQuery<Tuple> query = entityManager.createQuery(jpql, Tuple.class);
        query.setParameter("classCode", classCode);
        query.setParameter("statuses", statuses);

        List<Tuple> results = query.getResultList();

        if (results.isEmpty()) {
            return Optional.empty(); // or throw an exception if preferred
        }

        Tuple firstResult = results.getFirst();
        Lecturer lecturer = new Lecturer(
                firstResult.get("lecturerId", Long.class),
                firstResult.get("lecturerFirstName", String.class),
                firstResult.get("lecturerLastName", String.class)
        );
        List<ClassParticipant> participants = results.stream()
                .filter(result -> result.get("status", LearnStatus.class) == LearnStatus.REGISTERED)
                .map(result -> new ClassParticipant(
                        result.get("studentId", Long.class),
                        result.get("studentCode", String.class),
                        result.get("studentFirstName", String.class),
                        result.get("studentLastName", String.class),
                        result.get("groupName", String.class),
                        result.get("programCode", String.class),
                        result.get("programName", String.class)
                )).toList();
        List<ClassParticipant> waitingList = results.stream()
                .filter(result -> result.get("status", LearnStatus.class) == LearnStatus.WAITING)
                .map(result -> new ClassParticipant(
                        result.get("studentId", Long.class),
                        result.get("studentCode", String.class),
                        result.get("studentFirstName", String.class),
                        result.get("studentLastName", String.class),
                        result.get("groupName", String.class),
                        result.get("programCode", String.class),
                        result.get("programName", String.class)
                )).toList();
        OpeningClassDetails openingClassDetails = new OpeningClassDetails(
                firstResult.get("moduleClassId", Long.class),
                firstResult.get("moduleClassCode", String.class),
                firstResult.get("moduleName", String.class),
                firstResult.get("numberOfCredits", Integer.class),
                lecturer,
                List.of(), // Schedules can be added here if needed
                firstResult.get("maxParticipants", Integer.class),
                participants.size(),
                participants,
                waitingList
        );

        return Optional.of(openingClassDetails);
    }
}
