package cn.jxj4869.blog.service.impl;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.mapper.CommentMapper;
import cn.jxj4869.blog.service.ICommentService;
import cn.jxj4869.blog.utils.MainUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {


    private final Integer PAGE_SIZE = 10;



    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MainUtil mainUtil;


    @Override
    public MyPage<Comment> commentPage(Integer currentPage, Integer blogId) {
        MyPage<Comment> page = new MyPage<>(currentPage, PAGE_SIZE);
        MyPage<Comment> myPage = commentMapper.selectParentCommentNullPage(page, blogId);
        myPage.setShowBtnNum();
        return myPage;
    }


    @Override
    public MyPage<Comment> commentAllPage(Integer currentPage) {
        MyPage<Comment> page=new MyPage<>(currentPage,PAGE_SIZE);
        QueryWrapper<Comment> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createTime");
        MyPage<Comment> commentMyPage = commentMapper.selectPage(page, wrapper);
        commentMyPage.setShowBtnNum();
        return commentMyPage;
    }


    @Async
    public void replyRemind(Comment comment) {
        int cnt = 0;
        while (cnt < 4) {
            cnt++;
            try {
                String content = "您在博客《" + comment.getBlogTitle() + "》的评论有回复了";
                mainUtil.sendReplyRemind(comment.getEmail(), content);

                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public Info commentSave(Comment comment) {
        Info info = new Info();
        comment.setCreateTime(new Date());

        if (comment.getParentCommentId().equals(-1)) {
            comment.setParentCommentId(null);
        } else {
            Comment parentComment = commentMapper.selectById(comment.getParentCommentId());
            if (parentComment.isRemind()) {
                replyRemind(parentComment);
            }
        }

        if (comment.getReplyCommentId().equals(-1)) {
            comment.setReplyCommentId(null);
        }


        int cnt = commentMapper.insert(comment);

        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "保存成功");
        } else {
            info.put("flag", false);
            info.put("msg", "保存失败");
        }

        return info;
    }


    @Override
    @Transactional
    public Info commentDelete(Integer cid) {
        Info info = new Info();
        int cnt = commentMapper.deleteById(cid);

        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "删除成功");
        } else {
            info.put("flag", false);
            info.put("msg", "删除失败");
        }
        return info;
    }
}
