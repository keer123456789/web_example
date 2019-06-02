package com.example.web_example.Controller;

import com.example.web_example.ContractUtil.ContractUtil;
import com.example.web_example.Service.web3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.tx.Contract;

import java.util.Map;

@RestController
public class web3Controller {

    @Autowired
    web3Service  web3Service;

    @PostMapping("/addUser")
    public String addUser(@RequestBody Map map) throws Exception {
        return web3Service.addUser(map);
    }

    @GetMapping("/getUserInfo/{address}")
    public String getUserInfo(@PathVariable String address) throws Exception {
        return web3Service.getUserInfo(address);
    }
}
