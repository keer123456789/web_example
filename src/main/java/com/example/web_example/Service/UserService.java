package com.example.web_example.Service;

import com.example.web_example.Domain.User;
import com.example.web_example.Domain.WebResult;


public interface UserService {

    WebResult addUser(User user);

    WebResult get();


}
