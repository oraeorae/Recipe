package com.example.demo.dao;


import com.example.demo.pojo.Mall;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallMapper {
    /**
     * 页面只返回一张图片，名字，id
     */
    @Select("select goodsName,goodsDescribe,goodsId,goodsPicture,goodsPrice from mall limit #{first},#{second};")
    List<Mall> listMall(int first, int second);

    /**
     * 页面返回部分信息
     */
    @Select("select goodsName,goodsDescribe,goodsId,goodsPicture,goodsPrice from mall where goodsType=#{type} limit #{first},#{second};")
    List<Mall> listTypeMall(String type, int first, int second);

    /**
     * 页面返回具体信息
     */
    @Select("select * from mall where goodsId=#{id};")
    Mall getMall(String id);


    /**
     * 页面返回查找的信息（显示于页面上）
     */
    @Select("select goodsName,goodsDescribe,goodsId,goodsPicture,goodsPrice from mall where goodsName like CONCAT('%',#{find},'%') limit #{first},#{second};")
    List<Mall> searchMall(String find,int first,int second);

    /**
     * 页面返回查找的信息（显示于页面上）
     */
    @Select("select goodsName,goodsDescribe,goodsId,goodsPicture,goodsPrice from mall where goodsName like CONCAT('%',#{find},'%');")
    List<Mall> searchSepicMall(String find);


    /**
     * 添加新的商城信息
     */
    @Insert("insert into mall(goodsName,goodsPlace,goodsDescribe,goodsType,goodsDetails,goodsPicture,goodsPrice) values(#{goodsName},#{goodsPlace},#{goodsDescribe},#{goodsType},#{goodsDetails},#{goodsPicture},#{goodsPrice})")
    int insertMall(Mall mall);


}