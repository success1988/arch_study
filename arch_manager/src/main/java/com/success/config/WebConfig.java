package com.success.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/7/11 10:40
 * @Description
 * @Version
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //默认静态资源处理
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

}
