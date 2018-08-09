package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private String WELCOME_MESSAGE ;
    public WelcomeController(@Value("${WELCOME_MESSAGE}") String hello) {
        this.WELCOME_MESSAGE  = hello;
    }

    @GetMapping("/")
    public String sayHello() {
        return WELCOME_MESSAGE ;
    }


}

//