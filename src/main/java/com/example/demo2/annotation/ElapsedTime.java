package com.example.demo2.annotation;

import java.lang.annotation.*;

/**
 * 统计方法运行耗时
 * @author zhanghao
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElapsedTime {
}
