package com.example.demo.utils;

import io.jsonwebtoken.*;

import java.util.Date;

//学习网站：https://www.jianshu.com/p/578a7b2f3e8d

/**
 * jwt工具类
 */
public class JwtUtils {
    /**
     * 实例
     */
    private static JwtUtils instance;

    /**
     * 发行者
     */
    private String subObject = "wechatbyorall";

    /**
     * 过期时间，默认1天
     */
    private long expired = 1000 * 60 * 60 * 24 * 1;

    /**
     * jwt构造
     */
    private static JwtBuilder jwtBuilder;

    /**
     * 密钥
     */
    private String secret = "UYRL@sdjow114da5#KXKRWF$1124gki";// 密钥

    /**
     * 获取实例
     *
     * @return
     */
    public static JwtUtils getInstance() {
        if (instance == null) {
            instance = new JwtUtils();
        }
        jwtBuilder = Jwts.builder();
        return instance;
    }

    /**
     * 荷载信息(通常是一个User信息，还包括一些其他的元数据)
     *
     * @param key
     * @param val
     * @return
     */
    public JwtUtils setClaim(String key, Object val) {
        jwtBuilder.claim(key, val);
        return this;
    }

    /**
     * 生成 jwt token
     *
     * @return
     */
    public String generateToken() {
        String token = jwtBuilder
                .setSubject(subObject) // 发行者
                //.claim("id","121") // 参数
                .setIssuedAt(new Date()) // 发行时间
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .signWith(SignatureAlgorithm.HS256, secret) // 签名类型 与 密钥
                .compressWith(CompressionCodecs.DEFLATE)// 对载荷进行压缩
                .compact(); // 压缩一下
        return token;
    }

    /**
     * 解析 token
     *
     * @param token
     * @return
     */
    public Claims check(String token) {
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String getSubObject() {
        return subObject;
    }

    /**
     * 设置发行者
     *
     * @param subObject
     * @return
     */
    public JwtUtils setSubObject(String subObject) {
        this.subObject = subObject;
        return this;
    }

    public long getExpired() {
        return expired;
    }

    /**
     * 设置过期时间
     *
     * @param expired
     * @return
     */
    public JwtUtils setExpired(long expired) {
        this.expired = expired;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    /**
     * 设置密钥
     *
     * @param secret
     * @return
     */
    public JwtUtils setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
