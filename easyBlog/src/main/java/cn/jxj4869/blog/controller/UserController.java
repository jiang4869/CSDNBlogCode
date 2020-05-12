package cn.jxj4869.blog.controller;


import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.User;
import cn.jxj4869.blog.mapper.UserMapper;
import cn.jxj4869.blog.service.IUserService;
import cn.jxj4869.blog.utils.CheckCodeGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.attoparser.dom.INode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
@Controller
@RequestMapping("/user")
public class UserController {




    @Autowired
    IUserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {

        Map<String, Object> map = CheckCodeGenerator.generateCodeAndPic();
        session.setAttribute("checkCode", map.get("code").toString());
        ImageIO.write((RenderedImage) map.get("codePic"), "PNG", response.getOutputStream());
    }

    @PostMapping("/checkCode")
    @ResponseBody
    public Map<String, Object> getCheckCode(String checkCode, HttpSession session) {
        Info info = new Info();
        String code = (String) session.getAttribute("checkCode");
        System.out.println(code);
        if (code != null && code.equalsIgnoreCase(checkCode)) {
            info.put("flag", true);
        } else {
            info.put("flag", false);
        }
        return info;
    }

    @RequestMapping("/findUsername")
    @ResponseBody
    public Map<String, Object> findUsername(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        User user = userService.getOne(wrapper);
        Info info = new Info();
        if (user != null) {
            info.put("flag", false);
            info.put("msg", "用户已存在");
        } else {
            info.put("flag", true);
        }
        return info;
    }

    @RequestMapping("/findEmail")
    @ResponseBody
    public Info findEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        User user = userService.getOne(wrapper);
        System.out.println("email "+user);
        Info info = new Info();
        if (user != null) {
            info.put("flag", false);
            info.put("msg", "邮箱已被注册");
        } else {
            info.put("flag", true);
        }
        return info;
    }



    @RequestMapping("/checkOriginPassword")
    @ResponseBody
    public Info checkOriginPassword(String userName,String originPassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", userName);
        User user = userService.getOne(wrapper);
        System.out.println("userName "+user);
        Info info = new Info();
        if(user!=null && user.getPassword().equals(originPassword)) {
            info.put("flag",true);
        }else{
            info.put("flag", false);
            info.put("msg", "密码错误");
        }

        return info;
    }



    @PostMapping("/register")
    @ResponseBody
    public Info register(User user) {
        System.out.println(user);

        return userService.register(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public Info login(@RequestParam("userName") String userName, @RequestParam("password") String password,HttpSession session,HttpServletResponse response) {
        Info info = userService.login(userName, password);
        System.out.println("login====================");
        if((boolean)info.get("flag")==true) {

            User user=(User)info.get("user");

            session.setAttribute("user",user);
            if(user.getUserName().equals("admin")) {
                session.setAttribute("admin",user);
            }


        }
        return info;
    }


    @RequestMapping("/logout")
    @ResponseBody
    public Info logout(HttpSession session) {
        Info info = new Info();
        session.removeAttribute("user");
        session.removeAttribute("admin");
        info.put("flag",true);
        return info;
    }


    @RequestMapping("/update/avatar")
    @ResponseBody
    @Transactional
    public Info updateAvatar(@RequestParam("url") String url,@RequestParam("uid") Integer id,HttpSession session) {
        Info info = new Info();
        User suser = (User) session.getAttribute("user");
        if(!suser.getUid().equals(id)) {
            info.put("flag",false);
            info.put("msg","没有操作权限");
            return info;
        }
        User user = new User();
        user.setUid(id).setAvatar(url);
        user.setUpdateTime(new Date());
        boolean flag = userService.updateById(user);
      if(flag) {
          User user1 =(User) session.getAttribute("user");
          user1.setAvatar(url);
          session.setAttribute("user",user1);
      }

        info.put("flag",flag);
       return info;
    }


    @PostMapping("/updateUserInfo")
    @ResponseBody
    @Transactional
    public Info updateUserInfo(User user, HttpSession session, Model model) {
        Info info = new Info();
        if(user.getPassword()!=null &&user.getPassword().equals("")) {
            user.setPassword(null);
        }
        user.setUpdateTime(new Date());
        boolean flag = userService.updateById(user);
        if(flag) {
            info.put("flag",true);
            info.put("msg","更新成功");
            user=userService.getById(user.getUid());
            session.setAttribute("user",user);
            model.addAttribute("user",user);
        }else{
            info.put("flag",false);
            info.put("msg","失败成功");
        }

        return info;
    }


    @DeleteMapping("/edit/{uid}")
    @ResponseBody
    @Transactional
    public Info userDelete(@PathVariable("uid") Integer uid,HttpSession session) {
        Info info = new Info();
        User user = (User) session.getAttribute("user");
        if(!user.getUserName().equals("admin")) {
            info.put("flag",false);
            info.put("msg","没有操作权限");
            return info;
        }
        int cnt = userMapper.deleteById(uid);

        if(cnt==1) {
            info.put("flag",true);
            info.put("msg","删除成功");
        }else{
            info.put("flag",false);
            info.put("msg","失败成功");
        }

        return info;
    }

}
