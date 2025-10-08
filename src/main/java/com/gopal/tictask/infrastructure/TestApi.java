package com.gopal.tictask.infrastructure;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TestApi {

    @GetMapping("say-hello")
    public String getMethodName() {
        return "Hello Gopal Shivhare";
    }

    @PostMapping("/test-body")
public void testBody(@RequestBody String body) {
    System.out.println("BODY ===> " + body);
}
    
    
}
