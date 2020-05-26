package com.example.fileserver.security;

import com.example.fileserver.handler.MyAccessDeniedHandler;
import com.example.fileserver.handler.SecurityAuthenticationHandler;
import com.example.fileserver.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author ltx
 * @Date 21:50 2020/5/8
 *
 * SecurityConfig
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityAuthenticationHandler securityAuthenticationHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/query/file-list").hasRole("TEACHER")
//                .antMatchers("/test/*").hasRole("TEACHER")
                .and()
                .csrf().disable();

        http.addFilterAt(securityFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new TokenFilter(), HeaderWriterFilter.class);
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(securityAuthenticationHandler);
    }

    private SecurityFilter securityFilter(AuthenticationManager authenticationManager){
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setAuthenticationManager(authenticationManager);
        return securityFilter;
    }

    private SecurityFilter securityFilter(){
        SecurityFilter securityFilter = new SecurityFilter();
        return securityFilter;
    }
}
