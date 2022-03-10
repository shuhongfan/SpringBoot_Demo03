package com.shf;

import com.shf.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot11DubboZookeeperConsumerApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.buyTicket();
    }

}
