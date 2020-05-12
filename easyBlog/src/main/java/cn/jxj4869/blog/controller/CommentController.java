package cn.jxj4869.blog.controller;


import cn.jxj4869.blog.entity.*;
import cn.jxj4869.blog.mapper.BlogMapper;
import cn.jxj4869.blog.service.ICommentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Delete;
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
 * <p>
 * 还没添加安全控制，防止直接用URL访问，操作数据出错等情况。
 *
 * @author jxj4869
 * @since 2020-05-06
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @Autowired
    BlogMapper blogMapper;

    @RequestMapping("/list/{blogId}")
    public String commentList(@PathVariable("blogId") Integer blogId, @RequestParam(value = "pageNum", defaultValue = "1") Integer page, Model model) {

        MyPage<Comment> myPage = commentService.commentPage(page, blogId);
        model.addAttribute("page", myPage);
        List<Comment> comments = myPage.getRecords();
        model.addAttribute("comments", comments);


        return "blog/blogcontent::commentslist";
    }

    @RequestMapping("post")
    public String post(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
//            处理为登录的情况
            return "blog/unlogin";
        }

//        判断是否可评论，防止直接通过url链接提交评论
        Blog blog = blogMapper.selectById(comment.getBid());
        if (!blog.getPublished() || !blog.getIsComment()) {
            return "error";
        }

        System.out.println(comment);
        Info info = commentService.commentSave(comment);
        return "redirect:/comment/list/" + comment.getBid();
    }

    @PostMapping("delete")
    public String post(@RequestParam("cid") Integer cid, @RequestParam("bid") Integer blogId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comment byId = commentService.getById(cid);
        Info info = new Info();
        if (user == null || !user.getUid().equals(byId.getUid())) {
            return "blog/commenterror";
        }
        info = commentService.commentDelete(cid);

        return "redirect:/comment/list/" + blogId;
    }


    @DeleteMapping("/admin/delete/{cid}")
    @ResponseBody
    public Info post(@PathVariable("cid") Integer cid, HttpSession session, Model model) {
        Info info = new Info();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getUserName().equals("admin")) {
            info.put("flag",false);
            info.put("msg","请先登录");
            return info;
        }
        info = commentService.commentDelete(cid);

        return info;
    }


}
