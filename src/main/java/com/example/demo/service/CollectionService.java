package com.example.demo.service;

import com.example.demo.pojo.Collection;
import com.example.demo.pojo.Mall;
import com.example.demo.pojo.Recipe;
import java.util.List;

public interface CollectionService {
    /**
     * 功能:显示商店部分信息
     * @return 返回每张页面的信息
     */
    List<Collection> listCollectionMall(String openid);

    /**
     * 功能:显示菜谱部分信息
     * @return 返回每张页面的信息
     */
    List<Collection> listCollectionRecipe(String openid);

    /**
     * 功能:判断当前是否收藏
     * @return 返回每张页面的信息
     */
    boolean Collection(String id,String type,String openid);

    /**
     * 添加新的收藏信息
     */
    boolean insertCollection(Collection collection);

    /**
     * 删除收藏信息
     */
    boolean deleteCollection(String id,String type,String openid);


}