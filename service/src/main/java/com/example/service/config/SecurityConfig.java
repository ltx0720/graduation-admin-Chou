package com.example.service.config;

import com.example.service.filter.SecurityFilter;
import com.example.service.filter.TokenFilter;
import com.example.service.handler.SecurityAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/c_server/*").hasRole("STU")
//                .antMatchers("/test/*").hasRole("STU")
//                .antMatchers("/test/*").hasAnyRole("STU", "TEA")
//                .antMatchers("/s_server/*").hasAuthority("ROLE_STU")
                .and()
                .csrf().disable();

        http.addFilterBefore(new TokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(securityFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
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
