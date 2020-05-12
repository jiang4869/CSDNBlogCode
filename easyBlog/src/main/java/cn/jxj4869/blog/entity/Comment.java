package cn.jxj4869.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;
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
@TableName("blog_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    private Integer uid;

    private Integer bid;

    @TableField("createTime")
    private Date createTime;

    private String content;

    @TableField("parentCommentId")
    private Integer parentCommentId;

    @TableField("replyCommentId")
    private Integer replyCommentId;

    private String email;


    private boolean remind;


    @TableField(select = false)
    List<Comment> replyComments;

    @TableField(select = false)
    private Comment replyToComment; //回复哪一条评论，若评论本身父评论则为null

    @TableField(select = false)
    private User user;

    @TableField(select = false)
    private String blogTitle;
}
