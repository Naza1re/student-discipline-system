package com.example.studentsystem.controller;

import com.example.studentsystem.exception.UserNotFoundException;
import com.example.studentsystem.model.User;
import com.example.studentsystem.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public String allUsers(Model model){
        List<User> allusers = userService.findeAll();
        model.addAttribute("allusers",allusers);
        return "all-users";
    }
    @GetMapping()
    public String adminPage(Model model) {
        List<User> users = userService.findeAll();
        model.addAttribute("allusers", users);
        return "for-admin";
    }
    @GetMapping("/{user_id}/edit-delete")
    public String editDeletePage(Model model,@PathVariable Long user_id) throws UserNotFoundException {
        User user = userService.findById(user_id);
        model.addAttribute("user",user);
        return "edit-delete-user";
    }
    @PostMapping("/{user_id}/edit-delete")
    public String editDelete(@ModelAttribute User user , @PathVariable Long user_id) throws UserNotFoundException {
        userService.updateUser(user,user_id);
        return "redirect:/main";
    }
    @PostMapping("/{user_id}/delete")
    public String deleteUser(@PathVariable Long user_id) throws UserNotFoundException {
        userService.deleteUser(user_id);
        return "redirect:/main";
    }

    @GetMapping("/add-user")
    public String registrateUserPage(){
        return "registration";
    }
    
    @PostMapping("/add-user")
    public String registrateUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/main";
    }
}
