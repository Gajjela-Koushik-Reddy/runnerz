package com.koushikreddy.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class JdbcClientRunRepository {

    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class) // This line is used to map the rows of the database into the mapped class
                .list(); // This line is used to return the list of mapped objects
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select id, title, started_on, completed_on, miles, location from Run where id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var updated = jdbcClient
                .sql("insert into Run(id, title, started_on, completed_on, miles, location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(),
                        run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run" + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql(
                "update Run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(),
                        id))
                .update();

        Assert.state(updated == 1, "Failed to create run" + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from Run where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to create run" + id);
    }

    public void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }

    public int count() {
        return jdbcClient.sql("select * from Run").query().listOfRows().size();
    }

}
