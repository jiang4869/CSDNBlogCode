package cn.jxj4869.blog.service;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.entity.vo.BlogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
public interface IBlogService extends IService<Blog> {

    public Info blogSave(Blog blog);

    public Info blogUpdate(Blog blog);

    public Info blogDelete(Integer blogId);

    public MyPage<Blog> blogListPage(BlogVo blogVo);

    public MyPage<Blog> blogListPage(Integer uid,Integer pageSize,Integer currentPage);

    public MyPage<Blog> blogAllPage(Integer uid,Integer pageSize,Integer currentPage);

    public MyPage<Blog> blogAllPage(Integer currentPage);
}
