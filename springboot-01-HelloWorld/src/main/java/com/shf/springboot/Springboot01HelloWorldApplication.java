package com.shf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication 标注这个类是一个springboot应用：启动类下所有资源被导入
@SpringBootApplication
public class Springboot01HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloWorldApplication.class, args);
    }

}
