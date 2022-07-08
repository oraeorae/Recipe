package com.example.demo.service;

import com.example.demo.pojo.Museum;

import java.util.List;

public interface MuseumService {
    /**
     * 功能:显示部分信息
     *
     * @param page 页数
     * @return 返回每张页面的信息
     */
    List<Museum> listMuseum(int page, int limit);


    /**
     * 功能:根据类型显示部分信息
     * @param type 类型
     * @return 返回每张页面的信息
     */
    List<Museum> listTypeMuseum(String type, int page, int limit);


    /**
     * 显示具体信息
     * @param id 标识符
     * @return 返回页面的具体信息
     */
    Museum getMuseum(String id);


    /**
     * 插入具体博物馆
     *
     * @param museum 实体类
     * @return 返回插入成功的列
     */
    int insertMuseum(Museum museum);


}