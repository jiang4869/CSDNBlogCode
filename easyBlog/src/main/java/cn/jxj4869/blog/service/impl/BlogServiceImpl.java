package cn.jxj4869.blog.service.impl;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.entity.vo.BlogVo;
import cn.jxj4869.blog.mapper.BlogMapper;
import cn.jxj4869.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    BlogMapper blogMapper;

    private final Integer PAGE_SIZE=10;


    @Override
    @Transactional
    public Info blogUpdate(Blog blog) {
        Info info = new Info();
        blog.setUpdateTime(new Date());
        int cnt = blogMapper.updateById(blog);
        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "更新成功");
        } else {
            info.put("flag", false);
            info.put("msg", "更新失败");
        }
        return info;
    }

    @Override
    public MyPage<Blog> blogListPage(BlogVo blogVo) {
        MyPage<Blog> page=new MyPage<>(blogVo.getPage(),PAGE_SIZE);
        QueryWrapper<Blog> wrapper=new QueryWrapper<>();
        wrapper.like(blogVo.getWord()!=null,"title",blogVo.getWord()).eq(blogVo.getType().equals("self"),"uid",blogVo.getUid()).eq("published",true)
                .orderByDesc(blogVo.getOrderBy().equals("updateTime"),"updateTime").orderByDesc(blogVo.getOrderBy().equals("view"),"views")
                .orderByDesc(blogVo.getOrderBy().equals("default"),"updateTime");
        MyPage<Blog> blogMyPage = blogMapper.selectPage(page, wrapper);
        blogMyPage.setShowBtnNum();
        return blogMyPage;
    }

    @Override
    public MyPage<Blog> blogListPage(Integer uid, Integer pageSize, Integer currentPage) {
        MyPage<Blog> page=new MyPage<>(currentPage,pageSize);
        QueryWrapper<Blog> wrapper=new QueryWrapper<>();
        wrapper.eq("uid",uid).eq("published",true).orderByDesc("createTime");
        MyPage<Blog> blogMyPage = blogMapper.selectPage(page, wrapper);
        blogMyPage.setShowBtnNum();
        return blogMyPage;
    }

    @Override
    public MyPage<Blog> blogAllPage(Integer uid, Integer pageSize, Integer currentPage) {
        MyPage<Blog> page=new MyPage<>(currentPage,pageSize);
        QueryWrapper<Blog> wrapper=new QueryWrapper<>();
        wrapper.eq("uid",uid).orderByDesc("createTime");
        MyPage<Blog> blogMyPage = blogMapper.selectPage(page, wrapper);
        blogMyPage.setShowBtnNum();
        return blogMyPage;
    }

    @Override
    public MyPage<Blog> blogAllPage(Integer currentPage) {
        MyPage<Blog> page=new MyPage<>(currentPage,PAGE_SIZE);
        QueryWrapper<Blog> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createTime");
        MyPage<Blog> blogMyPage = blogMapper.selectPage(page, wrapper);
        blogMyPage.setShowBtnNum();
        return blogMyPage;
    }

    @Override
    @Transactional
    public Info blogDelete(Integer blogId) {
        Info info = new Info();
        int cnt = blogMapper.deleteById(blogId);

        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "删除成功");
        } else {
            info.put("flag", false);
            info.put("msg", "删除失败");
        }

        return info;
    }

    /**
     * 新增博客的保存
     *
     * @param blog
     * @return
     */
    @Override
    @Transactional
    public Info blogSave(Blog blog) {
        Info info = new Info();
        blog.setViews(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int cnt = blogMapper.insert(blog);
        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "保存成功");
        } else {
            info.put("flag", false);
            info.put("msg", "保存失败");
        }

        return info;
    }
}
