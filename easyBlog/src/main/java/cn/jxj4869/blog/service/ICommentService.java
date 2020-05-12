package cn.jxj4869.blog.service;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.MyPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
public interface ICommentService extends IService<Comment> {

    public Info commentSave(Comment comment);

    public MyPage<Comment> commentPage(Integer currentPage,Integer blogId);

    public Info commentDelete(Integer cid);

    public MyPage<Comment> commentAllPage(Integer currentPage);

}
