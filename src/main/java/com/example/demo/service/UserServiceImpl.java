package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int isUser(String openid) {
        return userMapper.isUser(openid);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}