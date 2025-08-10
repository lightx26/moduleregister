package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent.persistence;

import com.pet.moduleregister.entities.moduleClassStudent.enums.LearnStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "module_class_student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleClassStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_class_student_id")
    private Long moduleClassStudentId;

    @Column(name = "module_class_id")
    private Long moduleClassId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LearnStatus status;

    @Column(name = "retake_count")
    private int retakeCount;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
