package com.example.web_example.Controller;

import com.example.web_example.Domain.User;
import com.example.web_example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Autowired
    UserService userService;

    @PostMapping("/post")
    public String post(@RequestBody User user){
        return userService.addUser(user);

    }

    @GetMapping("/get")
    public String get(){
        return  "helloworld";
    }
}
