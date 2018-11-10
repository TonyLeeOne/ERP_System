package com.tony.erp.service.shiro;

import com.tony.erp.domain.UrlConfigure;
import com.tony.erp.service.caffeine.CaffeineService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态配置url权限信息
 */
@Service
public class ShiroService {


    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private CaffeineService caffeineService;

    /**
     * 从缓存中读取所有的url配置信息
     * @return
     */
    public Map<String,String> loadFilterChainDefinitions(){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<UrlConfigure> configures=caffeineService.getAllUrls();
        configures.forEach(con->{
            filterChainDefinitionMap.put(con.getUrl(),con.getAuthority());
        });

        return filterChainDefinitionMap;
    }


    /**
     * 动态更新url权限信息
     */
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

        // 清空老的权限配置
        manager.getFilterChains().clear();

        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(loadFilterChainDefinitions());

        // 重新构建生成新的权限配置
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
