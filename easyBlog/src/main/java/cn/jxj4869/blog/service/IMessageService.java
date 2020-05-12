package cn.jxj4869.blog.service;

import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.Message;
import cn.jxj4869.blog.entity.MyPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
public interface IMessageService extends IService<Message> {

    public MyPage<Message> messagePage(Integer currentPage);

    public Info messageDelete(Integer mid);

    public Info messageSave(Message message);

    public MyPage<Message> messageAllPage(Integer currentPage);


}
