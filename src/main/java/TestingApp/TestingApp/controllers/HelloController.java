package TestingApp.TestingApp.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TestingApp.TestingApp.Repositories.UserRepo;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final UserRepo userRepo;

    public HelloController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/{FirstName}")
    public String helloFirstName(@PathVariable("FirstName") String FirstName) {
        return userRepo.findByFirstName(FirstName)
        .map(u -> "Hello, " + u.getFirstName() + " " + u.getLastName() + "!")
        .orElse("No User Found");
        
    }

}
