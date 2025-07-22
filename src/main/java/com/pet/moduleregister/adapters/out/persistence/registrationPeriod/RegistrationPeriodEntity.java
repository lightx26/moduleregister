package com.pet.moduleregister.adapters.out.persistence.registrationPeriod;

import com.pet.moduleregister.domain.registrationPeriod.enums.RegistrationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "registration_period")
@Getter
@Setter
public class RegistrationPeriodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_period_id")
    private Long registrationPeriodId;

    @Column(name = "semester_id")
    private Long semesterId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RegistrationType type;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
