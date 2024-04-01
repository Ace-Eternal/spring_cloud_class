package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId){
        return new User(userId,"provider","provider");
    }
}
