package com.koushikreddy.runnerz.run;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import jakarta.annotation.PostConstruct;

public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(
                1,
                "Morning Run",
                LocalDateTime.of(2023, 5, 15, 6, 30),
                LocalDateTime.of(2023, 5, 15, 7, 45),
                5,
                Location.INDOOR // San Francisco
        ));

        runs.add(new Run(
                2,
                "Afternoon Jog",
                LocalDateTime.of(2023, 5, 16, 16, 0),
                LocalDateTime.of(2023, 5, 16, 17, 15),
                3,
                Location.OUTDOOR // New York City
        ));

        runs.add(new Run(
                3,
                "Trail Run",
                LocalDateTime.of(2023, 5, 17, 10, 0),
                LocalDateTime.of(2023, 5, 17, 12, 30),
                8,
                Location.INDOOR // Seattle
        ));

    }
}
