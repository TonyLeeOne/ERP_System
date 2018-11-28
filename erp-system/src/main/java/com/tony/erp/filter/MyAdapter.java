package com.tony.erp.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jli2
 * @date 11/19/2018 4:10 PM
 **/
@Configuration
public class MyAdapter implements WebMvcConfigurer {

    @Value("${web.upload-path}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations(filePath);
    }
}
