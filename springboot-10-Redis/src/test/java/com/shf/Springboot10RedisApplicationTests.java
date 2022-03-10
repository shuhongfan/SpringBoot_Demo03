package com.shf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shf.pojo.User;
import com.shf.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Springboot10RedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("mykey", "shf");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test() throws JsonProcessingException {
//        真是的开发一般都使用json来传递对象
        User shf = new User("舒洪凡", 43);
//        转换为JSON
        String jsonUser = new ObjectMapper().writeValueAsString(shf);

        redisTemplate.opsForValue().set("user", jsonUser);

        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    public void  test1(){
        redisUtil.set("name", "武汉");
        System.out.println(redisUtil.get("name"));
    }
}
