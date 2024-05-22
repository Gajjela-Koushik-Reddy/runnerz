package com.koushikreddy.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(
                Integer id,
                @NotEmpty String title,
                LocalDateTime startedOn,
                LocalDateTime completedOn,
                @Positive Integer miles,
                Location location) {
}
