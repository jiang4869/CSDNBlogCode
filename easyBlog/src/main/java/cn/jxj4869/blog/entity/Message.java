package cn.jxj4869.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@TableName("blog_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    private Integer uid;

    @TableField("createTime")
    private Date createTime;

    private String content;

    @TableField("parentMessageId")
    private Integer parentMessageId;

    @TableField("replyMessageId")
    private Integer replyMessageId;

    private String email;

    @TableField("nickName")
    private String nickName;

    private String avatar;

    private boolean remind;



    @TableField(select = false)
    List<Message> replyMessages;

    @TableField(select = false)
    private Message replyToMessage; //回复哪一条留言
}
