package com.example.demo.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * status 状态码 0-表示失败，1-表示成功
 * error_code 错误码，一般在设计时定义
 * error_des 错误描述，一般在设计时定义
 * data 成功数据
 * msg 请求成功 / 请求失败
 *
 * @author czh
 */
@Data
public class StatusCode {
    /**
     * int status;
     * String error_code;
     * String error_des;
     * * Object data;
     */
    public static Map<String, Object> success(Object data) {
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("status", 1);
        tmp.put("data", data);
        tmp.put("msg", "请求成功");
        return tmp;
    }

    public static Map<String, Object> error(int error_code) {
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("status", 0);
        tmp.put("msg", "请求失败");
        tmp.put("error_code", error_code);
        String error_des = "";
        // 以下待完善
        if (error_code == 1001) {
            error_des = "参数无效";
        } else if (error_code == 1002) {
            error_des = "参数缺失";
        } else if (error_code == 2001) {
            error_des = "用户未登录";
        } else if (error_code == 3001) {
            error_des = "服务器错误";
        }
        tmp.put("error_des", error_des);
        return tmp;
    }

    public static Map<String, Object> error(int error_code, String error_des) {
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("status", 0);
        tmp.put("msg", "请求失败");
        tmp.put("error_code", error_code);
        tmp.put("error_des", error_des);
        return tmp;
    }

}
