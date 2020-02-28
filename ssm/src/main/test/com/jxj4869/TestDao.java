package com.jxj4869;

import com.jxj4869.dao.AccountDao;
import com.jxj4869.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestDao {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void test1(){
        Account account = accountDao.findByName("张三");
        System.out.println(account);
    }
}
