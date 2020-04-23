package cn.jxj4869.fileuploadserver1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.WeakCacheKey;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.save-path}")
    private String savePath;
    @Value("${file.upload.url}")
    private String url;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(url).addResourceLocations("classpath:"+savePath);
    }
}
