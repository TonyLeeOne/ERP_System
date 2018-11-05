package com.tony.blog.service;

import com.tony.blog.entity.ShiroConfigure;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroService {

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    CaffeineService service;

    public Map<String,String> loadFilterChainDefinitions(){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<ShiroConfigure> configures=service.getAllPerms();
        configures.forEach(con->{
            filterChainDefinitionMap.put(con.getUrl(),con.getAuthority());
        });

        return filterChainDefinitionMap;
    }

    public void updatePermission(){
        AbstractShiroFilter shiroFilter=null;
        try {
            shiroFilter= (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(
                    "get ShiroFilter from shiroFilterFactoryBean error!");
        }

        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                .getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                .getFilterChainManager();

        // 清空老的权限控制
        manager.getFilterChains().clear();

        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(loadFilterChainDefinitions());



        // 重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean
                .getFilterChainDefinitionMap();

        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim()
                    .replace(" ", "");
            manager.createChain(url, chainDefinition);
        }

    }
}
