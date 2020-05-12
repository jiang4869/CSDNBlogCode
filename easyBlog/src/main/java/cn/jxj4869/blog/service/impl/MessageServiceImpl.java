package cn.jxj4869.blog.service.impl;

import cn.jxj4869.blog.entity.Comment;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.Message;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.mapper.MessageMapper;
import cn.jxj4869.blog.service.IMessageService;
import cn.jxj4869.blog.utils.MainUtil;
import cn.jxj4869.blog.utils.Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    private final Integer PAGE_SIZE=10;

    @Autowired
    MessageMapper messageMapper;



    @Autowired
    MainUtil mainUtil;


    @Async
    public void replyRemind(Message message) {
       int cnt=0;
       while(cnt<4){
           cnt++;
           try {
               String content="您的留言有回复了，快点去查看吧";
               mainUtil.sendReplyRemind(message.getEmail(),content);
               return;
           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }


    @Override
    public MyPage<Message> messagePage(Integer currentPage) {
        MyPage<Message> page=new MyPage<>(currentPage,PAGE_SIZE);
        MyPage<Message> myPage = messageMapper.selectParentMessageNullPage(page);
        myPage.setShowBtnNum();
        return myPage;
    }


    @Override
    public MyPage<Message> messageAllPage(Integer currentPage) {
        MyPage<Message> page=new MyPage<>(currentPage,PAGE_SIZE);
        QueryWrapper<Message> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createTime");
        MyPage<Message> messageMyPage = messageMapper.selectPage(page, wrapper);
        messageMyPage.setShowBtnNum();
        return messageMyPage;
    }



    @Override
    @Transactional
    public Info messageDelete(Integer mid) {
        Info info = new Info();
        int cnt = messageMapper.deleteById(mid);

        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "删除成功");
        } else {
            info.put("flag", false);
            info.put("msg", "删除失败");
        }
        return info;
    }

    @Override
    @Transactional
    public Info messageSave(Message message) {
        Info info = new Info();

        message.setCreateTime(new Date());

        if(message.getParentMessageId().equals(-1)) {
            message.setParentMessageId(null);
        }else{
            Message parentMessage = messageMapper.selectById(message.getParentMessageId());
            if(parentMessage.isRemind()) {
                replyRemind(parentMessage);
            }
        }

        if(message.getReplyMessageId().equals(-1)) {
            message.setReplyMessageId(null);
        }

        if(message.getUid().equals(-1)) {
            message.setUid(null);
            message.setAvatar(Util.getAvatar());
        }

        int cnt = messageMapper.insert(message);



        if (cnt == 1) {
            info.put("flag", true);
            info.put("msg", "保存成功");
        } else {
            info.put("flag", false);
            info.put("msg", "保存失败");
        }

        return info;
    }
}
