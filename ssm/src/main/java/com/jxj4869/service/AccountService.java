package com.jxj4869.service;

import com.jxj4869.domain.Account;

import java.io.IOException;
import java.util.List;

public interface AccountService {


    public List<Account> findAll();

    public void saveAccount(Account account);

    public void updateAccount(Account account);

    public void transfer(String sourceName, String targetName,Double money);
}
