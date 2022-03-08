package com.shf.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import sun.plugin2.message.transport.SerializingTransport;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//加载指定的配置文件
@ConfigurationProperties(prefix = "person")
//@PropertySource(value = "classpath:shf.properties")
@Validated
public class Person {
//    @Value("${name}")
//    @Email(message = "邮箱格式不正确")
    private String lastName;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
}
