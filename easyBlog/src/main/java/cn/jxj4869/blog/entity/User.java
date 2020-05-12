package cn.jxj4869.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("blog_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField("userName")
    private String userName;

    @TableField("nickName")
    private String nickName;


    private String password;

    private String email;

    private String avatar;

    private String qq;

    @TableField("createTime")
    private Date createTime;

    @TableField("updateTime")
    private Date updateTime;


}
