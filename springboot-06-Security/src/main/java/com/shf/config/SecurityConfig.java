package com.shf.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        首页所有人可以访问,功能页只有对应有权限的人才能访问

//        请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

//        没有权限默认会到登录页面(定制登录页)
        http.formLogin()
                .loginPage("/toLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login");

//        关闭CSRF
        http.csrf().disable();

//        开启注销功能
        http.logout().logoutSuccessUrl("/");

//        开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
    }

//    认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("vip2","vip3")
                .and()
                .withUser("root")
                .password(new BCryptPasswordEncoder().encode("root"))
                .roles("vip1","vip2","vip3")
                .and()
                .withUser("guest")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("vip1");
    }
}
