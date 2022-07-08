package com.example.demo.controller;

import com.example.demo.annotations.Limit;
import com.example.demo.pojo.Recipe;
import com.example.demo.service.RecipeService;
import com.example.demo.utils.FileUploadUtils;
import com.example.demo.utils.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController         //注解可以使结果以Json字符串的形式返回给客户端
@RequestMapping(value = "/api/recipes")         //使链接还有一个 /api/
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping("/")
    @ApiOperation(value = "获取全部菜谱的关键信息")
    public Map<String, Object> listRecipe(@RequestParam("page") int page) { //获取前端传过来的code
        try {
            List<Recipe> tmp = recipeService.listRecipe(page, 8);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }

    @PostMapping("/type")
    @Limit(key = "recipe_type", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "获取指定类型菜谱的关键信息")
    public Map<String, Object> listTypeMall(@RequestParam("page") int page,@RequestParam("type") String type) { //获取前端传过来的code
        try {
            //System.out.println(type);
            List<Recipe> tmp = recipeService.listTypeRecipe(page, 8,type);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }

    @PostMapping("/{id}")
    @Limit(key = "recipe_id", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "获取具体菜谱信息")
    public Map<String, Object> getRecipe(@PathVariable(name = "id") String id) {
        //limit 为8
        try {
            Recipe tmp = recipeService.getRecipe(id);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }

    @PostMapping("/insert")
    @Limit(key = "recipe_insert", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "添加具体菜谱信息")
    public Map<String, Object> insertRecipe(@RequestParam MultipartFile file, @Valid Recipe recipe, HttpServletRequest request) {
        try {
            //保存文件
            String filename = FileUploadUtils.SaveServer(file, request);
            //用UUID来生成唯一的id
            //String id = UUID.randomUUID().toString();
            //把实体类中的picture设置成文件路径
            recipe.setPicture(filename);
            //recipe.setId(id);

            recipeService.insertRecipe(recipe);
            Map<String, Object> data = StatusCode.success("插入成功");
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }


    @PostMapping("/search")
    @Limit(key = "recipe_search", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "搜索菜谱信息")
    public Map<String, Object> search(@RequestParam("find") String find,@RequestParam("page") int page) {
        //limit 为8
        try {
            List<Recipe> tmp = recipeService.searchRecipe(find,page,8);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }


}
