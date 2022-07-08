package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;

//create table users(avatarUrl longtext not null,name varchar(50),openid varchar(70),primary key (openid));

/**
 * 用户实体类
 * avatarUrl 头像链接
 * name 昵称
 * @author czh
 */
@Data       //使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；
public class User implements Serializable {
    //private String avatarUrl;
    //private String name;
    private String openid;

    /*public User(String _avatarUrl, String _name, String _openid) {
        avatarUrl = _avatarUrl;
        name = _name;
        openid = _openid;
    }*/

    public User(String _openid) {
        openid = _openid;
    }
}