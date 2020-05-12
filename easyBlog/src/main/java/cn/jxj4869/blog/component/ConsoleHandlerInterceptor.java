package cn.jxj4869.blog.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsoleHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if(user==null){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/console/unLogin").forward(request,response);
            return false;
        }else{

            return true;
        }
    }
}
