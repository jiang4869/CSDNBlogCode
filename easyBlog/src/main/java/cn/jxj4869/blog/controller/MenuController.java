package cn.jxj4869.blog.controller;


import cn.jxj4869.blog.entity.vo.BlogVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MenuController {

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping({"/", "index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/bloglist")
    public void blogList( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("/blog/list").forward(request,response);

    }

}
