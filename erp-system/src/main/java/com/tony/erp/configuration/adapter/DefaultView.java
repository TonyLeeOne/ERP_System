package com.tony.erp.configuration.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jil2
 * 默认首页跳转到登录页面
 */

@Configuration
public class DefaultView implements WebMvcConfigurer {

    @Value("{web.upload-path}")
    private String imgPath;

    /**
     * 设置项目首页为登录界面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/user/login");
    }

    /**
     * 指定静态资源在磁盘的位置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+imgPath);
    }
}
