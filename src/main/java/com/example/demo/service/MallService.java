package com.example.demo.service;

import com.example.demo.pojo.Mall;

import java.util.List;

public interface MallService {
    /**
     * 功能:显示部分信息
     *
     * @param page 页数
     * @return 返回每张页面的信息
     */
    List<Mall> listMall(int page, int limit);

    /**
     * 功能:根据类型显示部分信息
     *
     * @param page 页数
     * @param type 类型
     * @return 返回每张页面的信息
     */
    List<Mall> listTypeMall(int page, int limit, String type);

    /**
     * 显示具体信息
     *
     * @param id 标识符
     * @return 返回页面的具体信息
     */
    Mall getMall(String id);

    /**
     * 插入具体菜谱
     *
     * @param find 查找内容
     * @return 返回插入成功的数组
     */
    List<Mall> searchMall(String find,int page, int limit);

    /**
     * 插入具体菜谱
     *
     * @param recipe 实体类
     * @return 返回插入成功的列
     */
    int insertMall(Mall recipe);


}