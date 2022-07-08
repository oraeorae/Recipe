package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 商城实体类
 * 0.图片集
 * 1.商品名字
 * 2.商品产地
 * 3.商品描述
 * 4.商品详情
 * 5.id唯一标识符
 * @author czh
 */
//create table mall(goodsId int not null AUTO_INCREMENT,goodsName varchar(50) not null,
//goodsPlace varchar(80),goodsDescribe longtext,goodsType varchar(50) not null,goodsPicture longtext,goodsDetails longtext,primary key (goodsId));

@Data       //使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；
@JsonInclude(JsonInclude.Include.NON_NULL)   // 忽略返回参时值为null的字段
public class Mall implements Serializable {
    private String goodsPicture;
    private String goodsName;
    private String goodsPlace;
    private String goodsDescribe;
    private String goodsId;
    private String goodsType;
    private String goodsDetails;
    private String goodsPrice;
}
