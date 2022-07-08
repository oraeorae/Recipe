package com.example.demo.service;

import com.example.demo.dao.MallMapper;
import com.example.demo.pojo.Mall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    MallMapper mallMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<Mall> listMall(int page, int limit) {

        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Mall> listMall = (List<Mall>) redisTemplate.opsForValue().get("listMall_"+page);

        if (null == listMall) {
            //去数据库查询
            //{(page -1) * limit},#{limit};")
            int first = (page - 1) * limit;
            int second = limit;
            listMall = mallMapper.listMall(first, second);
            //只返回第一张图片
            for( Mall t : listMall ){
                String picture = t.getGoodsPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setGoodsPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("listMall_"+page, listMall, 30, TimeUnit.SECONDS);
        }
        return listMall;
    }

    @Override
    public List<Mall> listTypeMall(int page, int limit, String type) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Mall> listMall = (List<Mall>) redisTemplate.opsForValue().get("listMall_"+page+"_"+type);

        if (null == listMall) {
            //去数据库查询
            //{(page -1) * limit},#{limit};")
            int first = (page - 1) * limit;
            int second = limit;
            listMall =  mallMapper.listTypeMall(type, first, second);
            //只返回第一张图片
            for( Mall t : listMall ){
                String picture = t.getGoodsPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setGoodsPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("listMall_"+page+"_"+type, listMall, 30, TimeUnit.SECONDS);
        }
        return listMall;
    }

    @Override
    public Mall getMall(String id) {

        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        Mall mall = (Mall) redisTemplate.opsForValue().get("Mall_"+id);
        if (null == mall) {
            //去数据库查询
            mall = mallMapper.getMall(id);
            //并放入redis缓存
            redisTemplate.opsForValue().set("Mall_"+id, mall, 30, TimeUnit.SECONDS);
        }
        return mall;
    }

    @Override
    public int insertMall(Mall Mall) {
        return mallMapper.insertMall(Mall);
    }

    @Override
    public List<Mall> searchMall(String find,int page, int limit) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Mall>  searchMall = (List<Mall>) redisTemplate.opsForValue().get("searchMall_"+find);

        if (null == searchMall) {
            //{(page -1) * limit},#{limit};")
            int first = (page - 1) * limit;
            int second = limit;
            //去数据库查询
            searchMall = mallMapper.searchMall(find,first,second);
            //只返回第一张图片
            for( Mall t : searchMall ){
                String picture = t.getGoodsPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setGoodsPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("searchMall_"+find, searchMall, 30, TimeUnit.SECONDS);
        }
        return searchMall;
    }

}