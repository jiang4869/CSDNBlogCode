package cn.jxj4869.blog.mapper;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.MyPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */

public interface BlogMapper extends BaseMapper<Blog> {


    @Override
    @Select("select * from blog_blog where bid=#{id}")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById"))
    })
    Blog selectById(Serializable id);


//    @Override
//    @Select("select * from blog_blog ${ew.customSqlSegment}")
//    MyPage<Blog> selectPage(MyPage<Blog> page,@Param(Constants.WRAPPER) Wrapper<Blog> queryWrapper);


    @Override
    @Select("select * from blog_blog ${ew.customSqlSegment}")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "user",column = "uid",one=@One(select = "cn.jxj4869.blog.mapper.UserMapper.selectById"))
    })
    <E extends IPage<Blog>> E selectPage(E page,@Param(Constants.WRAPPER)  Wrapper<Blog> queryWrapper);
}
