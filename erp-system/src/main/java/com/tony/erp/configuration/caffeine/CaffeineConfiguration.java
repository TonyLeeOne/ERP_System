package com.tony.erp.configuration.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.erp.constant.Constant;
import com.tony.erp.service.ModuleService;
import com.tony.erp.service.RoleService;
import com.tony.erp.service.shiro.UrlConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfiguration {

    @Autowired
    private UrlConfigureService urlConfigureService;

    @Autowired
    private ModuleService moduleService;


    @Autowired
    private RoleService roleService;
    /**
     * 初始化cacheLoader实例
     * @return
     */
    CacheLoader<Object,Object> cacheLoader(){
        CacheLoader<Object,Object> cacheLoader=new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object o) throws Exception {
                return null;
            }

            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue;
            }
        };
        return cacheLoader;
    }

    /**
     * 采用同步方式写入缓存，5s刷新一次
     * @return
     */
    @Bean
    LoadingCache<String,Object> loadingCache(){
        LoadingCache<String,Object> loadingCache= Caffeine.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .maximumSize(10000)
                .build(key->getFromDB(key));

        return loadingCache;
    }


    private Object getFromDB(String key){
        if(Constant.ALL_URLS.equals(key)){
            return urlConfigureService.getAllUrlsFromDB();
        }

        if(Constant.ALL_MODULES.equals(key)){
            return moduleService.getAllModules();
        }

        if(Constant.ALL_ROLES.equals(key)){
            return roleService.getAllRoles();
        }



        return null;
    }

}
