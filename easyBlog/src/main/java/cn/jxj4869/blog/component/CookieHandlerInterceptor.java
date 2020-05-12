package cn.jxj4869.blog.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class CookieHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
        cookie.setMaxAge(60 * 30);
//        cookie.setPath("/");
        response.addCookie(cookie);
        request.setAttribute("date",new Date().getTime());
        return true;

    }
}
