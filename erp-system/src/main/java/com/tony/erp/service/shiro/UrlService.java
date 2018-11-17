package com.tony.erp.service.shiro;

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
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
public class UrlService {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UrlConfigureService service;

    /**
     * 获取项目中所有url
     * @return
     */
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

    /**
     *获取新增url
     * @return
     */
    public Map<String, List<String>> getDifference(){
        List<UrlConfigure> origin=service.getAllUrlsFromDB();
        Map<String,List<String>> map=new HashMap<>(2);
        if(!CollectionUtils.isEmpty(origin)){
            List<String> urls=new ArrayList<>();
            origin.forEach(configure->{
                urls.add(configure.getUrl());
            });

            map.put("insert",ListUtils.difference(getAllUrls(),urls));
            map.put("delete",ListUtils.difference(urls,getAllUrls()));
            return map;
        }
        map.put("all",this.getAllUrls());
        return map;
    }
}
