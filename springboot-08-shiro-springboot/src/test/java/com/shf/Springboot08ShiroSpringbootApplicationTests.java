package com.shf;

import com.shf.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot08ShiroSpringbootApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("aaa"));
    }

}
