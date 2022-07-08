package com.example.demo.dao;

import com.example.demo.pojo.Museum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MuseumMapper {
    /**
     * 页面只返回一张图片，名字，id
     */
    @Select("select id,picture,title from museum limit #{first},#{second};")
    List<Museum> listMuseum(int first, int second);

    /**
     * 页面返回部分信息
     */
    @Select("select id,picture,title from museum where type=#{type} limit #{first},#{second};")
    List<Museum> listTypeMuseum(String type, int first, int second);


    /**
     * 页面返回具体信息
     */
    @Select("select * from museum where id=#{id};")
    Museum getMuseum(String id);

    /**
     * 插入页面
     * */
    @Insert("insert into museum(id,title,body,picture) values(#{id},#{title},#{body},#{picture})")
    int insertMuseum(Museum museum);

}
