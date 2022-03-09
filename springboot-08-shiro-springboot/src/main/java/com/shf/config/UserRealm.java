package com.shf.config;

import com.shf.pojo.User;
import com.shf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
//        拿到当前登录的用户对象
        Subject subject = SecurityUtils.getSubject();
//        拿到user对象
        User currentUser = (User) subject.getPrincipal();
//        设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

//        连接真实数据库查询数据
        User user = userService.queryUserByName(token.getUsername());

//        没有这个人
        if (user == null) {
            return null;
        }

        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

//        密码认证 shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
