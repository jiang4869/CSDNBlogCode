package cn.jxj4869.blog.controller;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.entity.User;
import cn.jxj4869.blog.mapper.CommentMapper;
import cn.jxj4869.blog.mapper.UserMapper;
import cn.jxj4869.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    IBlogService blogService;

    /**
     * 用户个人中心首页，任何人都可以访问
     *
     * @param userName
     * @param model
     * @return
     */
    @RequestMapping("{userName}")
    public String home(@PathVariable("userName") String userName, Model model) {
//        查找用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", userName);
        User user = userMapper.selectOne(wrapper);
        model.addAttribute("user", user);

//        获取最新博客列表 默认最新十篇博客
        MyPage<Blog> blogMyPage = blogService.blogListPage(user.getUid(), 10, 1);
        List<Blog> blogList = blogMyPage.getRecords();
        model.addAttribute("blogList", blogList);

//        获取最新评论

        List<Comment> comments = commentMapper.selectCurrentCommentByUid(user.getUid());
        model.addAttribute("comments", comments);

        return "console/console";
    }


    /**
     * 个人博客管理界面，可以管理所有博客不管是否发布
     * 改请求地址只能登录状态下，用户查看自己的信息，而不能查看其他人的
     *
     * @return
     */
    @RequestMapping({"/{userName}/blog/{page}", "/{userName}/blog"})
    public String blog(@PathVariable("userName") String userName, @PathVariable(value = "page", required = false) Integer page, Model model,HttpSession session) {
         User user = (User)session.getAttribute("user");

         if(!user.getUserName().equals(userName)) {
             return "console/unlogin";
         }


        if (page == null) {
            page = 1;
        }


        User user1 = userMapper.selectById(user.getUid());
        model.addAttribute("user", user1);

        //        获取博客列表
        MyPage<Blog> blogMyPage = blogService.blogAllPage(user.getUid(), 10, page);
        List<Blog> blogList = blogMyPage.getRecords();
        model.addAttribute("blogList", blogList);

        model.addAttribute("page",blogMyPage);

        return "console/consoleblog";
    }


    /**
     * 只有登录状态下的用户才能查看自己的个人信息界面
     * @param userName
     * @return
     */
    @RequestMapping("/{userName}/setting")
    public String setting(@PathVariable("userName") String userName,Model model,HttpSession session) {
        //        查找用户  废除
//        直接从session中获取

        User user = (User)session.getAttribute("user");


        if(!user.getUserName().equals(userName)) {
            return "console/unlogin";
        }

        User user1 = userMapper.selectById(user.getUid());
        model.addAttribute("user",user1);
        return "console/consolesetting";
    }

}
