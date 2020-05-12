package cn.jxj4869.blog;


import cn.jxj4869.blog.entity.*;
import cn.jxj4869.blog.entity.vo.BlogVo;
import cn.jxj4869.blog.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    BlogTypeMapper blogTypeMapper;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void blogTypeTest() {
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();

        List<BlogType> blogTypes = blogTypeMapper.selectList(wrapper);
        for (BlogType blogType : blogTypes) {
            System.out.println(blogType);
        }
    }


//    @Test
//    public void blogUpdateTest() {
//        Blog blog = new Blog();
//        blog.setBid(1);
//        blog.setViews(1);
//        int i = blogMapper.updateById(blog);
//        System.out.println(blog);
//        System.out.println(i);
//        Blog blog1 = blogMapper.selectById(1);
//        System.out.println(blog1);
//    }

    @Test
    public void blogSelectByIdTest() {
        Blog blog = blogMapper.selectById(1);
        System.out.println(blog);
    }


    @Test
    public void selectPageTest() {
        MyPage<Blog> page = new MyPage<>(1, 1);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        MyPage<Blog> blogMyPage = blogMapper.selectPage(page, wrapper);

        List<Blog> records = blogMyPage.getRecords();
        for (Blog blog : records) {
            System.out.println(blog);
        }
    }


    @Test
    public void selectParentCommentNullPageTest() {
        MyPage<Comment> page = new MyPage<>(1, 10);
        MyPage<Comment> myPage = commentMapper.selectParentCommentNullPage(page, 1);
        List<Comment> comments = myPage.getRecords();
        for (Comment comment : comments) {
            System.out.println(comment);
        }

    }

//    @Test
//    public void insertCommentTest(){
//        Comment comment=new Comment();
//        comment.setCreateTime(new Date());
//        comment.setUid(3);
//        comment.setBid(1);
//        comment.setParentCommentId(null);
//        comment.setReplyCommentId(null);
//        comment.setContent("评论");
//        comment.setRemind(true);
//        comment.setEmail("1121429190@qq.com");
//        int insert = commentMapper.insert(comment);
//        System.out.println(insert);
//        System.out.println(comment);
//    }



    @Test
    public void selectParentMessageNullPageTest() {
        MyPage<Message> page = new MyPage<>(1, 10);
        MyPage<Message> myPage = messageMapper.selectParentMessageNullPage(page);
        List<Message> messages = myPage.getRecords();
        for (Message message : messages) {
            System.out.println(message);
        }

    }


    @Test
    public void selectCurrentCommentByUidTest() {
        List<Comment> comments = commentMapper.selectCurrentCommentByUid(3);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

}
