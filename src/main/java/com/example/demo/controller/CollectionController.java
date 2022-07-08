package com.example.demo.controller;

import com.example.demo.annotations.Limit;
import com.example.demo.dao.CollectionMapper;
import com.example.demo.pojo.Collection;
import com.example.demo.service.CollectionService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.StatusCode;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController         //注解可以使结果以Json字符串的形式返回给客户端
@RequestMapping(value = "/api/collection")         //使链接还有一个 /api/
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @Limit(key = "collection_type", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @PostMapping("/type")
    @ApiOperation(value = "返回收藏页面")
    public Map<String, Object> listTypeCollection(@RequestParam("type") String type, @RequestParam("token") String token) { //获取前端传过来的code
        //获取请求时的token
        System.out.println(type);
        JwtUtils jwt = JwtUtils.getInstance();
        Claims claims = jwt.check(token);
        if (claims != null) {
            String openid = (String) claims.get("openid");
            try {
                List<Collection> tmp = null;
                if( "mall".equals(type) ){
                    tmp = collectionService.listCollectionMall(openid);
                    System.out.println(type);
                }else if( "recipe".equals(type) ){
                    tmp = collectionService.listCollectionRecipe(openid);
                }
                Map<String, Object> data = StatusCode.success(tmp);
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                return StatusCode.error(3001, "服务器内部错误：" + e.toString());
            }
        }else{
            //非法token
            return StatusCode.error(2001, "用户未登录");
        }
    }

    @Limit(key = "collection_insert", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @PostMapping("/insert")
    @ApiOperation(value = "添加收藏信息")
    public Map<String, Object> insertCollection(@Valid Collection collection,@RequestParam("token") String token) {

        //获取请求时的token
        JwtUtils jwt = JwtUtils.getInstance();
        Claims claims = jwt.check(token);
        if (claims != null) {
            try {
                String openid = (String) claims.get("openid");
                collection.setOpenid(openid);
                if( collectionService.insertCollection(collection) == true ) {
                    return StatusCode.success("插入成功");
                }else{
                    return StatusCode.success("插入失败，内容已存在");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
                return data;
            }
        }else{
            //非法token
            return StatusCode.error(2001, "用户未登录");
        }
    }

    @Limit(key = "collection_delete", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @PostMapping("/delete")
    @ApiOperation(value = "删除收藏信息")
    public Map<String, Object> deleteCollection(@RequestParam("id") String id,@RequestParam("type") String type,@RequestParam("token") String token) {

        //获取请求时的token
        JwtUtils jwt = JwtUtils.getInstance();
        Claims claims = jwt.check(token);
        if (claims != null) {
            try {
                String openid = (String) claims.get("openid");
                if( collectionService.deleteCollection(id,type,openid) == true){
                    return StatusCode.success("删除成功");
                }else{
                    return StatusCode.success("删除失败，内容不存在");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
                return data;
            }
        }else{
            //非法token
            return StatusCode.error(2001, "用户未登录");
        }
    }

    @Limit(key = "collection_exist", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @PostMapping("/exist")
    @ApiOperation(value = "是否存在收藏信息")
    public Map<String, Object> existCollection(@RequestParam("id") String id,@RequestParam("type") String type,@RequestParam("token") String token) {

        //获取请求时的token
        JwtUtils jwt = JwtUtils.getInstance();
        Claims claims = jwt.check(token);
        if (claims != null) {
            try {
                String openid = (String) claims.get("openid");
                if( collectionService.Collection(id,type,openid) == true){
                    return StatusCode.success("该收藏存在");
                }else{
                    return StatusCode.success("该收藏不存在");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
                return data;
            }
        }else{
            //非法token
            return StatusCode.error(2001, "用户未登录");
        }
    }

}
