package com.jxj4869.controller;

import com.jxj4869.domain.Account;
import com.jxj4869.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户web
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("表现层，查询所有");
        List<Account> accounts = accountService.findAll();
        model.addAttribute("list",accounts);
        return "list";
    }


    @RequestMapping("/save")
    public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("表现层，保存数据");
        // List<Account> accounts = accountService.findAll();
        accountService.saveAccount(account);

        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return ;
    }
    @RequestMapping("/update")
    public void update(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("表现层，更新数据");
        // List<Account> accounts = accountService.findAll();
        accountService.updateAccount(account);

        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return ;
    }

    @RequestMapping("/transfer")
    public void transfer(String sourceName, String targetName,Double money,HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("表现层,transfer");
      accountService.transfer(sourceName,targetName,money);


        response.sendRedirect(request.getContextPath()+"/account/findAll");

        return ;
    }
}
