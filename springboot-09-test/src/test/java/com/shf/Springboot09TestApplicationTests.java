package com.shf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void sendSimpleMail() {
//        一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("shf");
        mailMessage.setText("这是一封测试邮件");

        mailMessage.setTo("shuhongfan@live.com");
        mailMessage.setFrom("shuhongfan@88.com");

        mailSender.send(mailMessage);
    }

    @Test
    void sendMimeMessage() throws MessagingException {
//        一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("shf");
        helper.setText("<h1 style='color=red'>这是一封测试邮件</h1>",true);
//        附件
        helper.addAttachment("shf.png", new File("C:\\Users\\shuho\\Pictures\\shf.png"));

        helper.setTo("shuhongfan@live.com");
        helper.setFrom("shuhongfan@88.com");
        mailSender.send(mimeMessage);
    }

}
