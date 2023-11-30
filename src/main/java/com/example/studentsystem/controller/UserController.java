package com.example.studentsystem.controller;

import com.example.studentsystem.model.User;
import com.example.studentsystem.repository.UserRepository;
import com.example.studentsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class UserController {
    private final UserService userService;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/registration")
    public String createUser(@ModelAttribute User user){
        System.out.println(user);
        userService.createUser(user);
        return "redirect:/login";
    }


}
