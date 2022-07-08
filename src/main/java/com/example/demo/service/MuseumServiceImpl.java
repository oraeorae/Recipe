package com.example.demo.service;

import com.example.demo.dao.MuseumMapper;
import com.example.demo.pojo.Museum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author czh
 */
@Service
public class MuseumServiceImpl implements MuseumService {
    @Autowired
    MuseumMapper museumMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<Museum> listMuseum(int page, int limit) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Museum>  listmuseum = (List<Museum>) redisTemplate.opsForValue().get("listMuseum_"+page);

        if (null == listmuseum) {
            //去数据库查询
            //{(page -1) * limit},#{limit};")
            int first = (page - 1) * limit;
            int second = limit;
            listmuseum = museumMapper.listMuseum(first, second);

            //只返回第一张图片
            for( Museum t :listmuseum ){
                String picture = t.getPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("listMuseum_"+page, listmuseum, 30, TimeUnit.SECONDS);
        }

        return listmuseum;
    }

    @Override
    public List<Museum> listTypeMuseum(String type,int page, int limit) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Museum> listmuseum = (List<Museum>) redisTemplate.opsForValue().get("listMuseum_"+page+"_"+type);

        if (null == listmuseum) {
            //去数据库查询
            //{(page -1) * limit},#{limit};")
            int first = (page - 1) * limit;
            int second = limit;
            listmuseum =  museumMapper.listTypeMuseum(type, first, second);
            //只返回第一张图片
            for( Museum t : listmuseum ){
                String picture = t.getPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("listMuseum_"+page+"_"+type, listmuseum, 30, TimeUnit.SECONDS);
        }
        return listmuseum;
    }

    @Override
    public Museum getMuseum(String id) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        Museum  museum = (Museum) redisTemplate.opsForValue().get("museum_"+id);
        if (null == museum) {
            //去数据库查询
            museum = museumMapper.getMuseum(id);
            //并放入redis缓存
            redisTemplate.opsForValue().set("museum_"+id, museum, 30, TimeUnit.SECONDS);
        }
        return museum;
    }

    @Override
    public int insertMuseum(Museum museum) {
        return museumMapper.insertMuseum(museum);
    }



}