package com.shf.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

//在templates目录下面的所有页面,只能通过controller来跳转
@Controller
public class IndexController {
    @RequestMapping({"/", "/index.html"})
    public String index(Model model){
        return "index";
    }
}
