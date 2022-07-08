package com.example.demo.controller;

import com.example.demo.annotations.Limit;
import com.example.demo.pojo.Museum;
import com.example.demo.service.MuseumService;
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
@RequestMapping(value = "/api/museums")         //使链接还有一个 /api/
public class MuseumController {
    @Autowired
    MuseumService museumService;

    @PostMapping("/")
    @ApiOperation(value = "获取全部博物馆的关键信息")
    public Map<String, Object> listMuseum(@RequestParam("page") int page) { //获取前端传过来的code
        try {
            List<Museum> tmp = museumService.listMuseum(page, 8);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }

    @PostMapping("/type")
    @Limit(key = "museum_type", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "获取指定类型博物馆的关键信息")
    public Map<String, Object> listTypeMall(@RequestParam("page") int page,@RequestParam("type") String type) { //获取前端传过来的code
        try {
            System.out.println(type);
            List<Museum> tmp = museumService.listTypeMuseum(type, page,8);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }

    @PostMapping("/{id}")
    @Limit(key = "museum_id", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "获取具体博物馆信息")
    public Map<String, Object> getMuseum(@PathVariable(name = "id") String id) {
        //limit 为8
        try {
            Museum tmp = museumService.getMuseum(id);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }

    @PostMapping("/insert")
    @Limit(key = "museum_insert", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "添加具体博物馆信息")
    public Map<String, Object> insertMuseum(@RequestParam MultipartFile file, @Valid Museum museum, HttpServletRequest request) {
        try {
            //保存文件
            String filename = FileUploadUtils.SaveServer(file, request);
            //用UUID来生成唯一的id
            //String id = UUID.randomUUID().toString();
            //把实体类中的picture设置成文件路径
            museum.setPicture(filename);
            //Museum.setId(id);

            museumService.insertMuseum(museum);
            Map<String, Object> data = StatusCode.success("插入成功");
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }




}
