package cn.jxj4869.blog.config;

import cn.jxj4869.blog.component.AdminHandlerInterceptor;
import cn.jxj4869.blog.component.ConsoleHandlerInterceptor;
import cn.jxj4869.blog.component.CookieHandlerInterceptor;
import cn.jxj4869.blog.component.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMVCConfig  implements WebMvcConfigurer {




    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConsoleHandlerInterceptor()).addPathPatterns("/console/**").excludePathPatterns("/console/*","/console/*/","/console/unLogin");
        registry.addInterceptor(new AdminHandlerInterceptor()).addPathPatterns("/admin/**","/user/edit/**").excludePathPatterns("/admin/login");

        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/comment/**","/message/delete",
                "/user/checkOriginPassword","/user/update/avatar","/user/updateUserInfo",
                "/blog/edit/**",
                "/fileupload/**"

                ).excludePathPatterns("/comment/list/**");

        registry.addInterceptor(new CookieHandlerInterceptor()).addPathPatterns("/**");

    }
}
