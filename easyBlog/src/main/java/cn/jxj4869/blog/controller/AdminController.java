package cn.jxj4869.blog.controller;

import cn.jxj4869.blog.entity.*;
import cn.jxj4869.blog.service.IBlogService;
import cn.jxj4869.blog.service.ICommentService;
import cn.jxj4869.blog.service.IMessageService;
import cn.jxj4869.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    IBlogService blogService;


    @Autowired
    ICommentService commentService;

    @Autowired
    IMessageService messageService;

    @RequestMapping({"/",""})
    public String index() {
        return "admin/index";
    }


    @RequestMapping("/login")
    public String loginIndex() {
        return "admin/login";
    }

    @RequestMapping({"/userlist", "user/list/{page}"})
    public String userList(@PathVariable(value = "page", required = false) Integer page,Model model) {
        if (page == null) {
            page = 1;
        }
        MyPage<User> userMyPage = userService.userListPage(page);
        List<User> userList = userMyPage.getRecords();
        model.addAttribute("userList",userList);
        model.addAttribute("page",userMyPage);
        return "admin/adminuserlist";
    }



    @RequestMapping({"/bloglist","/blog/list/{page}"})
    public String blogList(@PathVariable(value = "page", required = false) Integer page,Model model) {
        if (page == null) {
            page = 1;
        }

        MyPage<Blog> blogMyPage = blogService.blogAllPage(page);
        List<Blog> blogList = blogMyPage.getRecords();
        model.addAttribute("blogList",blogList);
        model.addAttribute("page",blogMyPage);
        return "admin/adminbloglist";
    }

    @RequestMapping({"/commentlist","/comment/list/{page}"})
    public String commentList(@PathVariable(value = "page", required = false) Integer page,Model model) {
        if (page == null) {
            page = 1;
        }

        MyPage<Comment> commentMyPage = commentService.commentAllPage(page);
        List<Comment> commentList = commentMyPage.getRecords();
        model.addAttribute("commentList",commentList);
        model.addAttribute("page",commentMyPage);
        return "admin/admincommentlist";
    }


    @RequestMapping({"/messagelist","/message/list/{page}"})
    public String messageList(@PathVariable(value = "page", required = false) Integer page,Model model) {
        if (page == null) {
            page = 1;
        }

        MyPage<Message> messageMyPage = messageService.messageAllPage(page);
        List<Message> messageList = messageMyPage.getRecords();
        model.addAttribute("messageList",messageList);
        model.addAttribute("page",messageMyPage);
        return "admin/adminmessagelist";
    }



    @PostMapping("/login")
    @ResponseBody
    public Info login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session, Model model) {
        Info info = new Info();
        if (userName.equals("admin")) {
            info = userService.login(userName, password);
            System.out.println("login====================");
            if ((boolean) info.get("flag") == true) {
                User user = (User) info.get("user");
                session.setAttribute("admin", user);
                session.setAttribute("user", user);

            }
        } else {
            info.put("flag", false);
            info.put("msg", "登录失败");
        }

        return info;
    }


    @RequestMapping("/logout")
    @ResponseBody
    public Info logout(HttpSession session) {
        Info info = new Info();
        session.removeAttribute("admin");
        session.removeAttribute("user");
        info.put("flag", true);
        return info;
    }
}
