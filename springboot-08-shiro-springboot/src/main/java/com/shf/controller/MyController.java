package com.shf.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Subject  用户
 * SecurityManager  管理所有用户
 * Realm  连接数据
 */
@Controller
public class MyController {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password,Model model){
//        获取当前的用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

//        执行登录的方法,如果没有异常就说明OK
        try {
//                执行了登录操作
            subject.login(token);

        } catch (UnknownAccountException uae) {
//                用户名错误
            model.addAttribute("msg","用户名错误 There is no user with username of " + token.getPrincipal());
            return "/login";
        } catch (IncorrectCredentialsException ice) {
//                密码错误
            model.addAttribute("msg","密码错误  Password for account " + token.getPrincipal() + " was incorrect!");
            return "/login";
        } catch (LockedAccountException lae) {
//                用户被锁定
            model.addAttribute("msg","用户被锁定 The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            return "/login";
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
            model.addAttribute("msg",ae);
            return "/login";
        }

        return "/index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/logout")
    public String logout() {
        //        获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        subject.logout();
        return "index";
    }
}
