package com.koushikreddy.runnerz.run;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

/* 
 * Naming convention is the trick
 * It takes in findAllBy and then adding Location in the end
 * This will structure the query to find all runs by location 
 */
    List<Run> findAllByLocation(String location);
}
