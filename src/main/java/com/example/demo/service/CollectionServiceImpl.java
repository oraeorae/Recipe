package com.example.demo.service;

import com.example.demo.dao.CollectionMapper;
import com.example.demo.pojo.Collection;
import com.example.demo.pojo.Mall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;


    /**
     * 功能:显示部分信息
     * @return 返回每张页面的信息
     */
    @Override
    public List<Collection> listCollectionMall(String openid) {
        List<Collection> listMall = collectionMapper.listCollectionMall(openid);
        //只返回第一张图片
        for( Collection t : listMall ){
            String picture = t.getGoodsPicture();
            int f = picture.indexOf("|");
            if( f != -1 ) {
                t.setGoodsPicture(picture.substring(0, f));
            }
        }
        return listMall;
    }

    /**
     * 功能:显示部分信息
     * @return 返回每张页面的信息
     */
    @Override
    public List<Collection> listCollectionRecipe(String openid) {
        List<Collection> listRecipe = collectionMapper.listCollectionRecipe(openid);
        //只返回第一张图片
        for( Collection t : listRecipe ){
            String picture = t.getPicture();
            int f = picture.indexOf("|");
            if( f != -1 ) {
                t.setPicture(picture.substring(0, f));
            }
        }
        return listRecipe;
    }

    /**
     * 添加新的收藏信息
     */
    @Override
    public boolean insertCollection(Collection collection) {
        if( collectionMapper.Collection(collection.getItemtype(),collection.getOpenid(),collection.getItemid()) <= 0 ) {
            collectionMapper.insertCollection(collection);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 删除收藏信息
     */
    @Override
    public boolean deleteCollection(String id,String type,String openid) {
        if( collectionMapper.Collection(type,openid,id) <= 0 ){
            //不存在
            return false;
        }else{
            collectionMapper.deleteCollection(id,type,openid);
            return true;
        }
    }

    @Override
    public boolean Collection(String id,String type,String openid){
        if( collectionMapper.Collection(type,openid,id) <= 0 ){
            return false;
        }else{
            return true;
        }
    }
}