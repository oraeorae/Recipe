package com.example.demo.service;

import com.example.demo.dao.MallMapper;
import com.example.demo.dao.RecipeMapper;
import com.example.demo.pojo.Mall;
import com.example.demo.pojo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author czh
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeMapper recipeMapper;

    @Autowired
    MallMapper mallMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<Recipe> listRecipe(int page, int limit) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Recipe>  listRecipe = (List<Recipe>) redisTemplate.opsForValue().get("listRecipe_"+page);

        if (null == listRecipe) {
            //去数据库查询
            //{(page -1) * limit},#{limit};")
            int first = (page - 1) * limit;
            int second = limit;
            listRecipe = recipeMapper.listRecipe(first, second);
            //只返回第一张图片
            for( Recipe t : listRecipe ){
                String picture = t.getPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("listRecipe_"+page, listRecipe, 30, TimeUnit.SECONDS);
        }

        return listRecipe;
    }

    @Override
    public List<Recipe> listTypeRecipe(int page, int limit, String type) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Recipe>  listRecipe = (List<Recipe>) redisTemplate.opsForValue().get("listRecipe_"+page+"_"+type);

        if (null == listRecipe) {
            //去数据库查询
            int first = (page - 1) * limit;
            int second = limit;
            listRecipe = recipeMapper.listTypeRecipe(type, first, second);

            for( Recipe t : listRecipe ){
                //只返回第一张图片
                String picture = t.getPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setPicture(picture.substring(0, f));
                }
            }


            //并放入redis缓存
            redisTemplate.opsForValue().set("listRecipe_"+page+"_"+type, listRecipe, 30, TimeUnit.SECONDS);
        }

        return listRecipe;
    }

    @Override
    public Recipe getRecipe(String id) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        Recipe  recipe = (Recipe) redisTemplate.opsForValue().get("Recipe_"+id);
        if (null == recipe) {
            //去数据库查询
            recipe = recipeMapper.getRecipe(id);

            //只返回第一张图片
            String picture = recipe.getPicture();
            int f = picture.indexOf("|");
            if( f != -1 ) {
                recipe.setPicture(picture.substring(0, f));
                recipe.setPracticePicture(picture.substring(f+1,picture.length()));
            }

            //分割原材料
            List<Mall> tmp = new ArrayList<>();
            String materials = recipe.getMaterials();
            String[] material = materials.split("|");
            for(int i = 0 ; i < material.length ; i++ ){
                List<Mall> mall = mallMapper.searchSepicMall(material[i]);
                if( mall != null && mall.size() > 0)
                {
                    tmp.add(mall.get(0));
                }
            }
            recipe.setMall(tmp);




            //并放入redis缓存
            redisTemplate.opsForValue().set("Recipe_"+id, recipe, 30, TimeUnit.SECONDS);

        }
        return recipe;
    }

    @Override
    public int insertRecipe(Recipe recipe) {
        return recipeMapper.insertRecipe(recipe);
    }

    @Override
    public List<Recipe> searchRecipe(String find, int page, int limit) {
        //为提升系统性能和用户体验
        //首先在Redis缓存中查询，如果有，直接使用；如果没有，去数据库查询并放入redis缓存
        List<Recipe>  searchRecipe = (List<Recipe>) redisTemplate.opsForValue().get("searchRecipe_"+find);

        if (null == searchRecipe) {
            //去数据库查询
            int first = (page - 1) * limit;
            int second = limit;
            searchRecipe = recipeMapper.searchRecipe(find,first,second);
            //只返回第一张图片
            for( Recipe t : searchRecipe ){
                String picture = t.getPicture();
                int f = picture.indexOf("|");
                if( f != -1 ) {
                    t.setPicture(picture.substring(0, f));
                }
            }
            //并放入redis缓存
            redisTemplate.opsForValue().set("searchRecipe_"+find, searchRecipe, 30, TimeUnit.SECONDS);
        }
        return searchRecipe;
    }

}