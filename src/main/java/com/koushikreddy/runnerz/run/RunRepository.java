package com.koushikreddy.runnerz.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    public Optional<Run> findById(int id) {
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.remove(existingRun.get());
        }
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
