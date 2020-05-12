package cn.jxj4869.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


import java.sql.Blob;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    private Integer uid;

    private String title;

    @TableField(jdbcType = JdbcType.BLOB)
    private String content;

    private String summary;

    @TableField("firstPicture")
    private String firstPicture;

    private Integer views;

    @TableField("createTime")

    private Date createTime;

    @TableField("updateTime")
    private Date updateTime;

    private Boolean published;

    @TableField("isComment")
    private Boolean isComment;

    @TableField("typeId")
    private Integer typeId;

    @TableField(select = false)
    private String typeName;

    @TableField(select = false)
    private User user;

}
