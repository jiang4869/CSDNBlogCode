package cn.jxj4869.blog.service.impl;

import cn.jxj4869.blog.entity.Info;
import cn.jxj4869.blog.entity.MyPage;
import cn.jxj4869.blog.entity.User;
import cn.jxj4869.blog.mapper.UserMapper;
import cn.jxj4869.blog.service.IUserService;
import cn.jxj4869.blog.utils.Util;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final Integer PAGE_SIZE=10;


    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public Info register(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("userName",user.getUserName()).or().eq("email",user.getEmail());
        Info info=new Info();
        User user1 = userMapper.selectOne(wrapper);
        if(user1!=null) {
            info.put("flag",false);
            info.put("msg","用户名或者邮箱已被注册");
            return info;
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setAvatar(Util.getAvatar());

        int cnt = userMapper.insert(user);
        info.put("flag",true);
        info.put("msg","注册成功");
        return null;
    }


    @Override
    public Info login(String userName, String password) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("userName",userName);
        User user = userMapper.selectOne(wrapper);
        Info info=new Info();
        if(user!=null&&user.getPassword().equals(password)) {
            info.put("flag",true);
            info.put("msg","登录成功");
            info.put("user",user);
        }else{
            info.put("flag",false);
            info.put("msg","账户或密码错误");
        }
        return info;
    }


    @Override
    public MyPage<User> userListPage(Integer currentPage) {
        MyPage<User> page=new MyPage<>(currentPage,PAGE_SIZE);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        MyPage<User> userMyPage = userMapper.selectPage(page, wrapper);
        userMyPage.setShowBtnNum();
        return userMyPage;
    }
}
