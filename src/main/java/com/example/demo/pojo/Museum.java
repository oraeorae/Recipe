package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片
 * 标题
 * id
 * 正文
 * 类型
 * */

//create table museum(picture longtext,body longtext,title varchar(50),id int not null AUTO_INCREMENT,primary key (id));

@Data       //使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；
@JsonInclude(JsonInclude.Include.NON_NULL)   // 忽略返回参时值为null的字段
public class Museum implements Serializable {
    private String picture;
    private String title;
    private String id;
    private String body;
    private String type;
}
