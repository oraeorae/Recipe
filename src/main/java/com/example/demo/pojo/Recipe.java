package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


//create table food()

//create table food(id int not null AUTO_INCREMENT,dishes varchar(50) not null,regional varchar(50),culture longtext,efficacy longtext,
// efficacy longtext,materials longtext,practice longtext,type varchar(30) not null,picture longtext,pirmary key(id));

/**
 * 菜谱实体类
 * 0.图片集
 * 1.菜名
 * 2.所属地域及地域特色
 * 3.菜品文化
 * 4.菜品功效
 * 5.菜品原材料（链接到商城）
 * 6.做法分享
 * 7.类型
 * 8.id唯一
 * 9.菜品描述
 */
@Data       //使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；
@JsonInclude(JsonInclude.Include.NON_NULL)   // 忽略返回参时值为null的字段
public class Recipe implements Serializable {
    // 如果是字符串类型的数据,使用 @NotBlank 比 @NoNull 更好,因为 @NotBlank 不仅会校验 null 值,它还会校验空字符串
    private String picture;
    @NotBlank
    private String dishes;
    private String regional;
    private String culture;
    private String efficacy;
    private String materials;
    private String practice;
    private String practicePicture;
    @NotBlank
    private String type;
    private String id;
    private String describes;
    private List<Mall> mall;


}