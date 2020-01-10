package com.example.web_example.Controller;

import com.example.web_example.Domain.WebResult;
import com.example.web_example.Domain.User;
import com.example.web_example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/mysql")
public class HelloWorldController {
    @Autowired
    UserService userService;

    /**
     * 在数据库中添加用户信息
     * {
     *      "name":"keer",
     *      "password":"789456"
     * }
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public WebResult post(@RequestBody User user){
        return userService.addUser(user);
    }




}
