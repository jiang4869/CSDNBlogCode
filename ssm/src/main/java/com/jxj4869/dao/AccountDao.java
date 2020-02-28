package com.jxj4869.dao;


import com.jxj4869.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户dao接口
 */
@Repository
public interface AccountDao {


    @Select("select * from account")
    public List<Account> findAll();

    @Select("select * from account where id=#{uid}")
    public Account findById(Integer uid);

    @Select("select * from account where name=#{username}")
    public Account findByName(String username);

    @Insert("insert into account(name,money) values(#{name},#{money})")
    public void saveAccount(Account account);

    @Update("update account set money=${money} where id=#{id}")
    public void updateAccount(Account account);
}
