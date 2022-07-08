package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 收藏页面
 * itemid 物品id
 * openid 用户id
 * type 类型（菜谱还是商店）
*/
//create table collection(id int not null AUTO_INCREMENT,itemid varchar(50) not null,openid varchar(50) not null,type varchar(50),primary key(id));
@Data       //使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；
@JsonInclude(JsonInclude.Include.NON_NULL)   // 忽略返回参时值为null的字段
public class Collection implements Serializable {
    String itemid;
    String openid;
    String itemtype;
    //菜谱
    private String picture;
    private String dishes;
    private String type;
    private String id;

    //商品
    private String goodsPicture;
    private String goodsName;
    private String goodsPlace;
    private String goodsId;
    private String goodsType;
    private String goodsPrice;

}
