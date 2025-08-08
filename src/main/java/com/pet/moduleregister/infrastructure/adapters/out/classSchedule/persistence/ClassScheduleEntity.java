package com.pet.moduleregister.infrastructure.adapters.out.classSchedule.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Instant;

@Entity
@Table(name = "class_schedule")
@Getter
@Setter
public class ClassScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_schedule_id")
    private Long classScheduleId;

    @Column(name = "module_class_id")
    private Long moduleClassId;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_period")
    private int startPeriod;    // From 1 to 10

    @Column(name = "end_period")
    private int endPeriod;      // From 1 to 10

    @Column(name = "room")
    private String room;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
