package com.example.service.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author ltx
 * @Date 21:23 2020/5/9
 *
 * 权限校验
 */
@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
