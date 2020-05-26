package com.example.fileserver.filter;

import com.example.fileserver.pojo.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ltx
 * @Date 21:23 2020/5/9
 *
 * 自定义用户校验拦截器
 */
public class SecurityFilter extends AbstractAuthenticationProcessingFilter {


    public SecurityFilter() {
//        super(new AntPathRequestMatcher("/query/**"));
        super(new AntPathRequestMatcher("/*", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("SecurityFilter");
//        User user = (User)request.getAttribute("user");
        User user = new User("ROLE_TEACHER");
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        return this.getAuthenticationManager().authenticate(authentication);
    }
}
