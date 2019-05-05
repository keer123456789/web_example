package com.example.web_example.Controller;

import com.example.web_example.Domain.ParserResult;
import com.example.web_example.Domain.User;
import com.example.web_example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloWorldController {
    @Autowired
    UserService userService;

    @PostMapping("/post")
    public String post(@RequestBody User user){
        return userService.addUser(user);

    }

    @PostMapping("/addPower")
    public String  addPower(@RequestBody Map map) throws Exception {
        if(userService.addPower(map)){
            return "success";
        }else {
            return "fail";
        }
    }

    @GetMapping("/getPowerInfo/{powerID}")
    public Map getInfo(@PathVariable String powerID) throws Exception {
        return  userService.getPowerInfo(powerID);
    }

    @GetMapping("/work/{sql}")
    public ParserResult work(@PathVariable String sql){
        return userService.work(sql);
    }


}
