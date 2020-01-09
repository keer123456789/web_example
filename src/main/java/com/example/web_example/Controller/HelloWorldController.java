package com.example.web_example.Controller;

import com.example.web_example.Domain.BigchainDB.ParserResult;
import com.example.web_example.Domain.User;
import com.example.web_example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @Autowired
    UserService userService;

    @PostMapping("/post")
    public String post(@RequestBody User user){
        return userService.addUser(user);

    }



    @GetMapping("/work/{sql}")
    public ParserResult work(@PathVariable String sql){
        return userService.work(sql);
    }


}
