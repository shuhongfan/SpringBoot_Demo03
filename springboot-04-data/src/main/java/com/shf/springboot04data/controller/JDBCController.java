package com.shf.springboot04data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    查询数据库的所有信息
//    没有实体类,数据库中的东西,怎样获取
    @RequestMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @GetMapping("/addUser")
    public int addUser(){
        String sql = "insert into user values(7,'等方式','fssf')";
        int result = jdbcTemplate.update(sql);
        System.out.println(result);
        return result;
    }

    @GetMapping("/updateUser/{id}")
    public int updateUser(@PathVariable("id") int id){
        String sql = "update user set name=?, pwd=? where id="+id;

        Object[] objects = new Object[2];
        objects[0]="小明";
        objects[1] = "zzzzzz";

        int update = jdbcTemplate.update(sql,objects);
        return update;
    }

    @GetMapping("/deleteUser/{id}")
    public int deleteUser(@PathVariable("id") int id){
        String sql ="delete from user where id="+id;
        int update = jdbcTemplate.update(sql, id);
        return update;
    }
}
