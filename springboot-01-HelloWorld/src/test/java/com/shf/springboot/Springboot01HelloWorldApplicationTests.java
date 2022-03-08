package com.shf.springboot;

import com.shf.springboot.pojo.Dog;
import com.shf.springboot.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot01HelloWorldApplicationTests {

    @Autowired
    private Dog dog;


    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(dog);
        System.out.println(person);
    }

}
