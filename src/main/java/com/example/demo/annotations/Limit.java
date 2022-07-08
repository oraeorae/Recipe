package com.example.demo.annotations;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

// 学习网站：https://blog.csdn.net/qq_34217386/article/details/122100904

/**
 * @author czh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Limit {

    // 资源key
    String key() default "";

    // 最多访问次数
    double permitsPerSecond();

    // 时间
    long timeout();

    // 时间类型
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

    // 提示信息
    String msg() default "请求过于频繁,请稍后再试";

}
