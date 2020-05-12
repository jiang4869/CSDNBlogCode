package cn.jxj4869.blog;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.mapper.BlogMapper;

import cn.jxj4869.blog.utils.MarkdownUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MarkDownTest {


    @Autowired
    BlogMapper blogMapper;


    @Test
    public void markdownTest(){
//        Blog blog = blogMapper.selectById(1);
//
//        System.out.println(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
    }



    @Test
    public void markdownTest1(){
        Blog blog = blogMapper.selectById(33);
        System.out.println(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
    }
}
