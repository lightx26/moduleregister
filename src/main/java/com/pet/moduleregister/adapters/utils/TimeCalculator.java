package com.pet.moduleregister.adapters.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeCalculator {
    public static Duration durationUntil(LocalDateTime targetTime) {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, targetTime);
    }

    public static Duration durationUntilNextMidnight() {
        return durationUntil(
                LocalDateTime.now().toLocalDate().atStartOfDay().plusDays(1)
        );
    }
}
