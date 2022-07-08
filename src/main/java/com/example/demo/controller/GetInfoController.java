package com.example.demo.controller;

//获取信息

import com.alibaba.fastjson.JSONObject;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.RequestUtils;

import com.example.demo.utils.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController         //注解可以使结果以Json字符串的形式返回给客户端
@RequestMapping(value = "/api/getinfo")         //使链接还有一个 /api/
public class GetInfoController {

    /**
     *  小程序的测试号
     *  appid
     *  secret
     * */
    public static String appid = "wx023e3a4297441859";
    public static String secret = "e7056611f6888dfd25745ff9b9dae882" ;

    @Autowired
    UserService userService;

    @GetMapping("/cs")
    public Map<String, Object> cs(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg","helloworld");
        return map;
    }

    /**
     * 获取电话号码
     * 请求：POST
     * 链接：地址/api/getinfo/getphone
     * 参数：code
     *  Content-Type: application/json;
     * 返回
     json {
     "msg":"ok"
     }
     * */
    @PostMapping("getphone")
    @ApiOperation(value="获取用户手机号码")
    public Map<String, Object> getPhone(@RequestParam("code") String code){ //获取前端传过来的code

        try{
            System.out.println(code);

            //获取获取小程序全局唯一后台接口调用凭据（access_token）
            String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;

            //调用get请求去访问微信小程序自带的链接，将返回结果存储到jsonStringa中
            String jsonStringa =  RequestUtils.doGet(getTokenUrl);

            //String转JSON
            JSONObject jsonObject = JSONObject.parseObject(jsonStringa);

            //获取JSON数据中的access_token
            String access_token = jsonObject.getString("access_token");
            //提交参数
            String getPhoneUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+access_token;

            Map<String,Object> map =  new HashMap<String,Object>();

            //将code放到map中
            map.put("code",code);
            //Map格式转化成JSON格式
            JSONObject json = new JSONObject(map);

            //向微信小程序接口提交Post请求得到结果
            String jsonStringb = RequestUtils.doPostForm(getPhoneUrl,json);

            //String转JSON
            JSONObject jsonObject2 = JSONObject.parseObject(jsonStringb);
            HashMap hashMap = JSONObject.parseObject(jsonObject2.toJSONString(), HashMap.class);
            //请求成功
            if( 0 == (int)hashMap.get("errcode") ){
                Map<String, String> data = new HashMap<>();
                JSONObject tmp2 = (JSONObject)hashMap.get("phone_info");
                data.put("phone",(String)tmp2.get("phoneNumber"));
                //将结果存储下来
                return StatusCode.success(data);
            }else{
                return StatusCode.error((int)hashMap.get("errcode"),"获取失败");
            }
        }catch (Exception e){
            System.out.println(e);
            return StatusCode.error(3001,"服务器内部错误："+e.toString());
        }

    }

    //https://blog.csdn.net/qq_41432730/article/details/123617323
    //
    @PostMapping(value = "login")
    @ApiOperation(value="登录")
    public @ResponseBody Map<String,Object> login(@RequestParam("code") String code/*,@RequestParam("avatarUrl") String avatarUrl,@RequestParam("avatarUrl") String name*/) {
        Map<String, String> data = new HashMap<String, String>();
        try {
            //Get请求（登录凭证校验）
            String getAuthUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
            //进行get请求
            String jsonString = RequestUtils.doGet(getAuthUrl);
            //String转JSON，再json转为map
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            HashMap hashMap = JSONObject.parseObject(jsonObject.toJSONString(), HashMap.class);

            //注意这里要加上 hashMap.get("errcode") == null
            if ( hashMap.get("errcode") == null || 0 == (int) hashMap.get("errcode")) {                //请求成功
                //得到openid和session_key去生成3rd_session
                //这个生成3rd_session的方式自己决定即可，比如使用SHA或Base64算法都可以。例如：将session_key或openid+session_key作为SHA或Base64算法的输入，输出结果做为3rd_session来使用，同时要将openid，session_key，3rd_session三者关联存储到数据库中，方便下次拿3rd_session获取session_key或openid做其他处理。
                String openid = (String) hashMap.get("openid");
                String session_key = (String) hashMap.get("session_key");

                //判断是否注册过
                int tmp = userService.isUser(openid);
                if ( tmp == 0 ) {
                    //没有注册过
                    User user = new User(openid);
                    //插入数据库
                    userService.insertUser(user);
                }
                //生成token
                JwtUtils jwt = JwtUtils.getInstance();
                String token = jwt
                        .setClaim("openid",openid)
                        .generateToken();

                Map<String, String> tmp3 = new HashMap<>();
                tmp3.put("token",token);
                //将结果存储下来
                return StatusCode.success(tmp3);

            } else {
                return StatusCode.error((int) hashMap.get("errcode"),"获取失败");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return StatusCode.error(3001,"服务器内部错误："+e.toString());
        }

    }
}