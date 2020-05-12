package cn.jxj4869.blog.controller;


import cn.jxj4869.blog.entity.*;
import cn.jxj4869.blog.entity.vo.BlogVo;
import cn.jxj4869.blog.mapper.BlogMapper;
import cn.jxj4869.blog.mapper.BlogTypeMapper;
import cn.jxj4869.blog.service.IBlogService;
import cn.jxj4869.blog.service.ICommentService;
import cn.jxj4869.blog.utils.MarkdownUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogTypeMapper blogTypeMapper;

    @Autowired
    IBlogService blogService;

    @Autowired
    ICommentService commentService;

    @RequestMapping({"list", "list/{page}"})
    public String list(BlogVo blogVo, Model model, HttpSession session) {
        if (blogVo.getType().equals("self")) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return "blog/unlogin";
            } else {
                blogVo.setUid(user.getUid());
            }

        }
        model.addAttribute("type", blogVo.getType());
        model.addAttribute("blogVo", blogVo);
        MyPage<Blog> blogMyPage = blogService.blogListPage(blogVo);
        List<Blog> blogList = blogMyPage.getRecords();
        model.addAttribute("blogList", blogList);
        model.addAttribute("page", blogMyPage);
        System.out.println(blogVo);
        return "blog/bloglist";
    }


    /**
     * 新建博客或者修改博客
     *
     * @param model
     * @return
     */
    @RequestMapping({"edit"})
    public String edit(Model model) {
        Blog blog = new Blog();
//        if (id != null) {
//            blog = blogMapper.selectById(id);
//            System.out.println(blog);
//        }
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        List<BlogType> blogTypes = blogTypeMapper.selectList(wrapper);
        model.addAttribute("blog", blog);
        model.addAttribute("blogTypes", blogTypes);
        return "blog/blogedit";
    }

    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable(value = "id") Integer id, Model model, HttpSession session) {
        Blog blog = blogMapper.selectById(id);
        User user = (User) session.getAttribute("user");
        if (blog == null) {
            return "error/404";
        }
        if (!blog.getUid().equals(user.getUid())) {
            return "blog/unlogin";
        }
        System.out.println(blog);
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        List<BlogType> blogTypes = blogTypeMapper.selectList(wrapper);
        model.addAttribute("blog", blog);
        model.addAttribute("blogTypes", blogTypes);
        return "blog/blogedit";
    }


    /**
     * 编辑博客后保存的提交请求
     *
     * @param blog
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public Info postEdit(Blog blog, HttpSession session) {
        Info info = new Info();
        User user = (User) session.getAttribute("user");
        if (!blog.getUid().equals(user.getUid())) {
            info.put("flag", false);
            info.put("msg", "没有权限操作");
            return info;
        }
        System.out.println(blog);

        if (blog.getBid() == null) {
            info = blogService.blogSave(blog);
        } else {
            blog.setUpdateTime(new Date());
            info = blogService.blogUpdate(blog);
        }
        return info;
    }


    /**
     * 删除博客
     *
     * @param blogId
     * @return
     */
    @DeleteMapping("/edit/{blogId}")
    @ResponseBody
    public Info deleteBlog(@PathVariable("blogId") Integer blogId, HttpSession session) {
        Info info = new Info();
        User user = (User) session.getAttribute("user");
        Blog blog = blogMapper.selectById(blogId);
        if (blog == null) {
            info.put("flag", false);
            info.put("msg", "资源不存在");

        } else if (!blog.getUid().equals(user.getUid()) && !user.getUserName().equals("admin")) {
            info.put("flag", false);
            info.put("msg", "没有权限操作");

        } else {

            info = blogService.blogDelete(blogId);
        }
        return info;
    }

    @RequestMapping("{blogId}")
    @Transactional
    public String blogContent(@PathVariable("blogId") Integer blogId, Model model, HttpSession session) {
        Blog blog = blogService.getById(blogId);
        User user = (User) session.getAttribute("user");

        if ((blog == null) ||( blog.getPublished() == false && (user == null || !user.getUserName().equals(blog.getUid()) && !user.getUserName().equals("admin")))) {
            return "error/404";
        }
        blog.setViews(blog.getViews() + 1);
        Blog tmp = new Blog();
        tmp.setBid(blog.getBid());
        tmp.setViews(blog.getViews());
        //更新访问量
        boolean flag = blogService.updateById(tmp);
//        String content = blog.getContent().replace("\n", "\n\n");
//        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("blog", blog);
        //还有评论过后加进去

        MyPage<Comment> myPage = commentService.commentPage(1, blogId);
        model.addAttribute("page", myPage);
        List<Comment> comments = myPage.getRecords();
        System.out.println("comments");
        System.out.println(comments);
        model.addAttribute("comments", comments);
        return "blog/blogcontent";
    }



}
