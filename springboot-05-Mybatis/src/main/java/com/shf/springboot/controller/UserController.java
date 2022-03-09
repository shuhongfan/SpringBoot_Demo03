package com.shf.springboot.controller;

import com.shf.springboot.mapper.UserMapper;
import com.shf.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public String queryUserList(){
        List<User> users = userMapper.queryUserList();
        return users.toString();
    }

    @GetMapping("/queryUserById/{id}")
    public String queryUserById(@PathVariable("id") Integer id){
        User user = userMapper.queryUserById(id);
        return String.valueOf(user);
    }

    @GetMapping("/addUser")
    public String addUser(User user){
        if (user.getName() == null){
            user=new User(10,"sasad","54");
        }
        int i = userMapper.addUser(user);
        return String.valueOf(i);
    }

    @GetMapping("/updateUser")
    public String updateUser(User user){
        user=new User(10,"refwf","54444");

        int i = userMapper.updateUser(user);
        return String.valueOf(i);
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        int i = userMapper.deleteUser(id);
        return String.valueOf(i);
    }
}
