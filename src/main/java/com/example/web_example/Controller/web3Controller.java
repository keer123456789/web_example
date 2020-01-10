package com.example.web_example.Controller;

import com.example.web_example.Domain.WebResult;
import com.example.web_example.Service.Web3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/web3")
public class web3Controller {

    @Autowired
    Web3Service web3Service;

    /**
     * 在智能合约Lesson中添加新的用户信息
     *
     * @param map map结构
     *            {
     *            "address":"0x5e9525f8733914fd600c2ca8651f29f672804d67",
     *            "name":"keer",
     *            "age":"18"
     *            }
     * @return
     * @throws Exception
     */
    @PostMapping("/addUser")
    public WebResult addUser(@RequestBody Map map) throws Exception {
        return web3Service.addUser(map);
    }

    /**
     * 获得相应用户的信息
     *
     * @param address geth客户端的用户地址
     * @return 返回相应的用户信息
     * @throws Exception
     */
    @GetMapping("/getUserInfo/{address}")
    public WebResult getUserInfo(@PathVariable String address) throws Exception {
        return web3Service.getUserInfo(address);
    }

    /**
     * 更改相应用户的信息
     *
     * @param address geth客户端的用户地址
     * @param age     要更新的用户信息
     * @return
     * @throws Exception
     */
    @GetMapping("/changeUserAge/{address}/{age}")
    public WebResult changeUserAge(@PathVariable String address, @PathVariable String age) throws Exception {
        return web3Service.changeUserAge(address, age);
    }
}
