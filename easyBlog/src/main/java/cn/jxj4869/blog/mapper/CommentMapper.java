package cn.jxj4869.blog.mapper;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.MyPage;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
public interface CommentMapper extends BaseMapper<Comment> {

//    @TableField(select = false)
//    List<Comment> replyComments;
//
//    @TableField(select = false)
//    private Comment parentComment; //回复哪一条评论，父评论则为null


    @Override
    @Select("select *,title from blog_comment,blog_blog where cid=#{id} and blog_blog.bid=blog_comment.bid")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "blogTitle",column = "title"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById"))
    })
    Comment selectById(Serializable id);

    @Select("select * from blog_comment where parentCommentId is null and bid=#{blogId}")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many=@Many(select = "cn.jxj4869.blog.mapper.CommentMapper.selectByParentCommentId"))
    })
    MyPage<Comment> selectParentCommentNullPage(MyPage<Comment> page,Integer blogId);


    @Select("select * from blog_comment where parentCommentId=#{cid}")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "replyCommentId",column = "replyCommentId"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById")),
            @Result(property = "replyToComment",column = "replyCommentId",one=@One(select = "cn.jxj4869.blog.mapper.CommentMapper.selectById"))
    })
    List<Comment> selectByParentCommentId(Integer cid);






    @Select("select bc.*,bb.title from blog_comment bc,blog_blog bb where bc.bid=bb.bid and bb.uid=#{uid} order by createTime desc limit 10")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById")),
            @Result(property = "blogTitle",column = "title")
    })
    List<Comment> selectCurrentCommentByUid(Integer uid);


    @Override
    @Select("select blog_comment.*,title from blog_comment,blog_blog where blog_blog.bid=blog_comment.bid ${ew.customSqlSegment}")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "blogTitle",column = "title"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById"))
    })
    <E extends IPage<Comment>> E selectPage(E page,@Param(Constants.WRAPPER)  Wrapper<Comment> queryWrapper);
}
