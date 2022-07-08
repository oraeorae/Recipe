package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {
    int isUser(String openid);

    int insertUser(User user);
}