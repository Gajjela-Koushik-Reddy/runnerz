package com.koushikreddy.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushikreddy.runnerz.RunnerzApplication;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);


    public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        if(runRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Successfully wrote data to database");
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON Data", e);
            }
        } else {
            log.info("Runs already loaded");
        }
    }
}
