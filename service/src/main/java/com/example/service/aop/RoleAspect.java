package com.example.service.aop;

import com.example.service.annotation.Role;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author ltx
 * @Date 23:10 2020/5/8
 *
 * 处理自定义权限注解
 */
@Component
@Aspect
public class RoleAspect {

    @Pointcut("@annotation(com.example.service.annotation.Role)")
    public void pointcut(){}

    @Before("pointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Role annotation = method.getAnnotation(Role.class);
        String value = annotation.value();

        System.out.println("aop: " + value);
    }

}
