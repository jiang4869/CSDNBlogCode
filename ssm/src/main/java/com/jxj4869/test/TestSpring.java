package com.jxj4869.test;

import com.jxj4869.dao.AccountDao;
import com.jxj4869.domain.Account;
import com.jxj4869.service.AccountService;
import com.jxj4869.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSpring {

//    @Test
//    public void run1() {
//        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService as=(AccountService) ac.getBean("accountService");
//        List<Account> all = as.findAll();
//        for(Account account:all){
//            System.out.println(account);
//        }
//    }
//
    @Test
    public void testTransfer(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService as=(AccountService) ac.getBean("accountService");
        as.transfer("张三","李四",100d);
    }


}
