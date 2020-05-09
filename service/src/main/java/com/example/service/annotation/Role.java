package com.example.service.annotation;

import java.lang.annotation.*;

/**
 * @Author ltx
 * @Date 23:06 2020/5/8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Role {
    String value() default "user";
}
