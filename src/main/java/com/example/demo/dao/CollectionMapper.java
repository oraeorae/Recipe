package com.example.demo.dao;

import com.example.demo.pojo.Collection;
import com.example.demo.pojo.Mall;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//create table collection(id int not null AUTO_INCREMENT,itemid varchar(50) not null,openid varchar(50) not null,type varchar(50),primary key(id));
@Mapper
public interface CollectionMapper {
    /**
     * 筛选商店收藏信息
     */
    @Select("select m.goodsName,m.goodsDescribe,m.goodsId,m.goodsPicture,m.goodsPrice from mall m inner join collection c on m.goodsId = c.itemid  where c.type='mall' and c.openid=#{openid} ;")
    List<Collection> listCollectionMall(String openid);



    /**
     * 筛选菜谱收藏信息
     */
    @Select("select f.picture,f.dishes,f.id,f.describes from food f inner join collection c on f.id = c.itemid  where c.type='recipe' and c.openid=#{openid} ;")
    List<Collection> listCollectionRecipe(String openid);

    /**
     * 判断是否已存在
     */
    @Select("select count(*) from  collection where type=#{type} and openid=#{openid} and itemid=#{itemid} ;")
    int Collection(String type,String openid,String itemid);

    /**
     * 添加新的收藏信息
     */
    @Insert("insert into collection(itemid,openid,type) values(#{itemid},#{openid},#{itemtype})")
    int insertCollection(Collection collection);

    /**
     * 删除收藏信息
     */
    @Delete("delete from collection where itemid=#{id} and type=#{type} and openid=#{openid}")
    void deleteCollection(String id,String type,String openid);

}