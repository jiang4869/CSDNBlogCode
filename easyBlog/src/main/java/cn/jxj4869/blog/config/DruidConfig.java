package cn.jxj4869.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    @ConfigurationProperties("spring.datasource")

    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }
}
