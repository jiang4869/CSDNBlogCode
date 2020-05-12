package cn.jxj4869.blog.controller;


import cn.jxj4869.blog.entity.*;
import cn.jxj4869.blog.mapper.MessageMapper;
import cn.jxj4869.blog.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    IMessageService messageService;

    @RequestMapping({"/", ""})
    public String index(Model model) {
        MyPage<Message> myPage = messageService.messagePage(1);
        model.addAttribute("page",myPage);
        List<Message> comments = myPage.getRecords();
        model.addAttribute("messages",comments);
        return "message/message";
    }


    @RequestMapping("/list/{page}")
    public String listPage(@PathVariable("page") Integer page,Model model) {
        MyPage<Message> myPage = messageService.messagePage(page);
        model.addAttribute("page",myPage);
        List<Message> comments = myPage.getRecords();
        model.addAttribute("messages",comments);
        return "message/message::messagelist";
    }


    @RequestMapping("/post")
    public String post(Message message) {
        System.out.println(message);
        Info info = messageService.messageSave(message);
        return "redirect:/message/list/1";
    }


    @PostMapping("delete")
    public String delete(@RequestParam("mid") Integer mid, HttpSession session) {
        User user = (User)session.getAttribute("user");
        Message message = messageMapper.selectById(mid);
        if(user==null||message.getUid()==null || ! user.getUid().equals(message.getUid())) {
            return "message/messageerror";
        }
        Info info = messageService.messageDelete(mid);
        return "redirect:/message/list/1";
    }


    @DeleteMapping("/delete/{mid}")
    @ResponseBody
    public Info deleteAdmin(@PathVariable("mid") Integer mid, HttpSession session,Model model) {
        User user = (User)session.getAttribute("user");
        Info info=new Info();
        Message message = messageMapper.selectById(mid);
        if(user==null|| !user.getUserName().equals("admin")) {
            info.put("flag",false);
            info.put("msg","操作失败，检查是否登录");
            return info;
        }
         info = messageService.messageDelete(mid);
        return info;
    }



}
