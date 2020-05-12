package cn.jxj4869.blog.service;

import cn.jxj4869.blog.entity.Blog;
import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.entity.User;
import cn.jxj4869.blog.entity.vo.BlogVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jxj4869
 * @since 2020-05-06
 */
public interface IUserService extends IService<User> {

    public Info register(User user);

    public Info login(String userName,String password);


    public MyPage<User> userListPage(Integer currentPage);
}
