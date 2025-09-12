package com.gopal.tictask.infrastructure;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestApi {

    @GetMapping("say-hello")
    public String getMethodName() {
        return "Hello Gopal Shivhare";
    }
    
    
}
