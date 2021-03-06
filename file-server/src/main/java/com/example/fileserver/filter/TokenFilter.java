package com.example.fileserver.filter;

import com.example.fileserver.utils.TokenUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ltx
 * @Date 17:16 2020/5/8
 *
 * token 校验
 */
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (TokenUtil.checkToken(request)) {
            filterChain.doFilter(request, response);
        } else {
            // 由于 filter 先于 controller  , 全局异常无法处理
            // 先转发到专门处理 filter 中异常的 Controller 对应方法
            request.getRequestDispatcher("/filter/token_exception").forward(request, response);
        }
    }
}
