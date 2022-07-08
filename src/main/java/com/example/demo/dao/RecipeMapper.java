package com.example.demo.dao;


import com.example.demo.pojo.Recipe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecipeMapper {


    /**
     * 页面只返回一张图片，名字，id
     */
    @Select("select picture,dishes,id,describes from food limit #{first},#{second};")
    List<Recipe> listRecipe(int first, int second);

    /**
     * 页面返回部分信息
     */
    @Select("select picture,dishes,id,describes from food where type=#{type} limit #{first},#{second};")
    List<Recipe> listTypeRecipe(String type, int first, int second);

    /**
     * 页面返回具体信息
     */
    @Select("select * from food where id=#{id};")
    Recipe getRecipe(String id);

    /**
     * 页面返回查找的信息（显示于页面上）
     */
    @Select("select picture,dishes,id,describes from food where dishes like CONCAT('%',#{find},'%') limit #{first},#{second};")
    List<Recipe> searchRecipe(String find,int first,int second);

    /**
     * 添加新的菜谱信息
     */
    @Insert("insert into food(dishes,regional,culture,efficacy,materials,practice,type,id,picture,describes) values(#{dishes},#{regional},#{culture},#{efficacy},#{materials},#{practice},#{type},#{id},#{picture},#{describes})")
    int insertRecipe(Recipe recipe);


}