package com.example.web_example.Service;

import com.example.web_example.Domain.WebResult;

import java.util.Map;


public interface Web3Service {

    WebResult addUser(Map map) throws Exception;

    WebResult getUserInfo(String address) throws Exception;

    WebResult changeUserAge(String address, String age) throws Exception;
}
