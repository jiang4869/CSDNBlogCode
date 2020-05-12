package cn.jxj4869.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


@Component
public class MainUtil {


    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendReplyRemind(String email,String content){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("回复提醒");
        message.setText(content);
        message.setTo(email);
        message.setFrom(from);
//        message.setFrom(from);
        System.out.println(email);
        mailSender.send(message);
    }

    public  void sendCheckCodeEmail(String email,String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("验证码信息");
        message.setText(content);
        message.setTo(email);
        message.setFrom(from);
        System.out.println(email);
        mailSender.send(message);
    }
}
