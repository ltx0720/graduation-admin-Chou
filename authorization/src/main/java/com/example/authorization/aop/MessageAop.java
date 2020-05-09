package com.example.authorization.aop;

import com.example.authorization.utils.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 21:09 2020/4/12
 */
@Component
@Aspect
public class MessageAop {

    @Pointcut("execution(* com.example.authorization.controller.LoginController.tologin32131(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public void before(ProceedingJoinPoint joinPoint){
        //        System.out.println(data);
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // 返回的消息用 base64 编码
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String s = new String(Base64.decode(request.getParameter("data")));

//        request.setAttribute("data", "321");
    }
}
