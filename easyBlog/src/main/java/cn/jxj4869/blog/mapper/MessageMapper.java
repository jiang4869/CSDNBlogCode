package cn.jxj4869.blog.mapper;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.Message;
import cn.jxj4869.blog.entity.MyPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
public interface MessageMapper extends BaseMapper<Message> {


    @Select("select * from blog_message where parentMessageId is null")
    @Results(value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "mid", column = "mid"),
            @Result(property = "replyMessages", column = "mid", many = @Many(select = "cn.jxj4869.blog.mapper.MessageMapper.selectByParentMessageId"))
    })
    MyPage<Message> selectParentMessageNullPage(MyPage<Message> page);


    @Select("select *  from blog_message where parentMessageId=#{mid}")
    @Results(value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "mid", column = "mid"),
            @Result(property = "replyMessageId", column = "replyMessageId"),
            @Result(property = "replyToMessage", column = "replyMessageId", one = @One(select = "cn.jxj4869.blog.mapper.MessageMapper.selectById"))
    })
    List<Message> selectByParentMessageId(Integer mid);


    @Override
    @Select("select * from blog_message")
    @Results(value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "mid", column = "mid"),
    })
    <E extends IPage<Message>> E selectPage(E page, @Param(Constants.WRAPPER) Wrapper<Message> queryWrapper);
}
