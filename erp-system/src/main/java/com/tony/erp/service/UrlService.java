package com.tony.erp.service;

import com.tony.erp.domain.UrlConfigure;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Service
public class UrlService {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UrlConfigureService service;

    private List<String> getAllUrls() {
        RequestMappingHandlerMapping mappingHandlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mappingHandlerMapping.getHandlerMethods();
        List<String> urls = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            Set<String> pattern = info.getPatternsCondition().getPatterns();
            for (String url : pattern
            ) {
                urls.add(url);
            }
        }

        return urls;

    }

    public  List<String> getDifference(){
        List<UrlConfigure> origin=service.getAllUrlsFromDB();
        if(!CollectionUtils.isEmpty(origin)){
            List<String> urls=new ArrayList<>();
            origin.forEach(configure->{
                urls.add(configure.getUrl());
            });
            return ListUtils.difference(getAllUrls(),urls);
        }
        return Collections.emptyList();
    }
}
