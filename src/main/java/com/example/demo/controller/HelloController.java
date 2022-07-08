package com.example.demo.controller;

import com.example.demo.annotations.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController         //注解可以使结果以Json字符串的形式返回给客户端
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello SpringBoot";
    }
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Limit(key = "cs", permitsPerSecond = 1, timeout = 500, msg = "请求过于频繁,请稍后再试！")
    @GetMapping("/cs")
    public Map<String, Object> cs() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "helloworld");
        logger.error("cs");
        logger.info("cs");
        logger.debug("cs");

        return map;
    }

}
