package com.tony.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UrlService {

    @Autowired
    private WebApplicationContext context;


    /**
     * 获取项目中所有的url
     * @return
     */
    public List<String> getAllUrls(){
        RequestMappingHandlerMapping mapping=context.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo,HandlerMethod> map=mapping.getHandlerMethods();
        List<String> urls=new ArrayList<>();
        for (RequestMappingInfo info:map.keySet()){
            Set<String> pattern=info.getPatternsCondition().getPatterns();
            for(String url:pattern){
                urls.add(url);
            }
        }
        return urls;
    }

}
