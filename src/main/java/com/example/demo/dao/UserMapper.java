package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 判断用户是否存在
     */
    @Select("SELECT count(*) FROM users WHERE openid=#{openid}")
    int isUser(String openid);

    /**
     * 添加新的用户信息
     */
    @Insert("insert into users(openid) values(#{openid})")
    int insertUser(User user);
    //@Insert("insert into users(avatarUrl,name,openid) values(#{dishes},#{avatarUrl},#{name},#{openid})")
    //int insertUser(User user);

}