package com.shf.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
//    3.shiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        设置安全管理器
        bean.setSecurityManager(securityManager);

//        添加shiro的内置过滤器
        /**
         * anon: 无需认证就可以访问
         * authc: 必须认证了才能访问
         * user: 必须拥有 记住我 功能才能用
         * perms: 拥有对某个资源的权限才能访问
         * role:  拥有某个角色权限才能访问
         */
//        登录拦截
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        授权
        filterChainDefinitionMap.put("/user/add", "perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

//        设置登录的请求
        bean.setLoginUrl("/toLogin");

//        未授权页面
        bean.setUnauthorizedUrl("/unauthorized");

        return bean;
    }

//    2.DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

//    1.创建Realm  对象,需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


//    整合shiroDialect
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
