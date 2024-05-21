package com.koushikreddy.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {

    @GetMapping(value = "/hello")
    String home() {
        return "<h1>Hello Runner</h1>";
    }
}