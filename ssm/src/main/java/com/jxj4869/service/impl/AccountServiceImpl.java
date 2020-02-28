package com.jxj4869.service.impl;

import com.jxj4869.dao.AccountDao;
import com.jxj4869.domain.Account;
import com.jxj4869.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")

public class AccountServiceImpl  implements AccountService {


    @Autowired
    private AccountDao accountDao;




    @Override
    public List<Account> findAll() {
        System.out.println("业务层，查询所有账户");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层，保存账户");
        accountDao.saveAccount(account);

    }

    public void updateAccount(Account account){
        System.out.println("业务层，更新账户");
        System.out.println(account);
        accountDao.updateAccount(account);
        int i=1/0;
        Account account1=new Account();
        account1.setMoney(1d);
        account1.setId(2);
        accountDao.updateAccount(account1);
    }

    public void transfer(String sourceName, String targetName,Double money){
        System.out.println("业务层，transfer....");
        //2.1根据名称查询转出账户

        Account source = accountDao.findByName(sourceName);
        //2.2根据名称查询转入账户
        Account target = accountDao.findByName(targetName);
        //2.3转出账户减钱
        source.setMoney(source.getMoney()-money);
        //2.4转入账户加钱
        target.setMoney(target.getMoney()+money);
        //2.5更新转出账户
        accountDao.updateAccount(source);

//            int i=1/0;


        //2.6更新转入账户
        accountDao.updateAccount(target);
    }

}
