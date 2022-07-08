package com.example.demo.controller;

import com.example.demo.annotations.Limit;
import com.example.demo.pojo.Mall;
import com.example.demo.service.MallService;
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
@RequestMapping(value = "/api/mall")         //使链接还有一个 /api/
public class MallController {
    @Autowired
    MallService mallService;

    @PostMapping("/")
    @ApiOperation(value = "获取全部商品的关键信息")
    public Map<String, Object> listMall(@RequestParam("page") int page) { //获取前端传过来的code
        try {
            List<Mall> tmp = mallService.listMall(page, 8);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error(3001, "服务器内部错误：" + e.toString());
        }
    }

    @PostMapping("/type")
    @Limit(key = "mall_type", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "获取指定类型商品的关键信息")
    public Map<String, Object> listTypeMall(@RequestParam("page") int page,@RequestParam("type") String type) { //获取前端传过来的code
        try {
            List<Mall> tmp = mallService.listTypeMall(page, 8,type);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error(3001, "服务器内部错误：" + e.toString());
        }
    }

    @PostMapping("/{id}")
    @Limit(key = "mall_id", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "获取具体商品信息")
    public Map<String, Object> getMall(@PathVariable(name = "id") String id) {
        //limit 为8
        try {
            Mall tmp = mallService.getMall(id);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error(3001, "服务器内部错误：" + e.toString());
        }
    }

    @PostMapping("/insert")
    @Limit(key = "mall_insert", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "添加具体菜谱信息")
    public Map<String, Object> insertMall(@RequestParam MultipartFile file, @Valid Mall mall, HttpServletRequest request) {
        try {
            //保存文件
            String filename = FileUploadUtils.SaveServer(file, request);
            //用UUID来生成唯一的id
            //String id = UUID.randomUUID().toString();
            //把实体类中的picture设置成文件路径
            mall.setGoodsPicture(filename);
            //Mall.setId(id);

            mallService.insertMall(mall);
            Map<String, Object> data = StatusCode.success("插入成功");
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }


    @PostMapping("/search")
    @Limit(key = "mall_search", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @ApiOperation(value = "搜索商品信息")
    public Map<String, Object> search(@RequestParam("find") String find,@RequestParam("page") int page) {
        //limit 为8
        try {
            List<Mall> tmp = mallService.searchMall(find,page,8);
            Map<String, Object> data = StatusCode.success(tmp);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> data = StatusCode.error(3001, "服务器内部错误：" + e.toString());
            return data;
        }
    }


}
