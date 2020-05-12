package cn.jxj4869.blog.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("admin");
        if(user==null){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/admin/login").forward(request,response);
            return false;
        }else{
            return true;
        }
    }
}
