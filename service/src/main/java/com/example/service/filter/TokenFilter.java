package com.example.service.filter;

import com.example.service.utils.TokenUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author ltx
 * @Date 17:16 2020/5/8
 *
 * token 校验
 */
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (TokenUtil.checkToken(req)){
            chain.doFilter(request, response);
        }else {
            // 由于 filter 先于 controller, 全局异常无法处理
            // 先转发到专门处理 filter 中异常的 Controller 对应方法
            request.getRequestDispatcher("/filter/token_exception").forward(request, response);
        }
    }
}
