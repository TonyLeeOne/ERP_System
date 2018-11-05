package com.tony.blog.configuration;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.blog.constant.Constant;
import com.tony.blog.dao.BlogMapper;
import com.tony.blog.dao.ModuleMapper;
import com.tony.blog.service.ProverbService;
import com.tony.blog.service.RoleService;
import com.tony.blog.service.ShiroConfigureService;
import com.tony.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    @Autowired
    private ProverbService proverbService;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ShiroConfigureService shiroConfigureService;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

    @Bean
    LoadingCache<String,Object> loadingCache(){
        LoadingCache<String,Object> loadingCache=Caffeine.newBuilder()
                .refreshAfterWrite(5,TimeUnit.SECONDS)
                .maximumSize(10000)
                .build(key->getFromCache(key));
        return loadingCache;
    }

    private Object getFromCache(String key){
        if(key.equals(Constant.PROVERB_RANDOM)){
            return proverbService.getAllProverbs();
        }
        if(key.equals(Constant.BLOGS)){
            return blogMapper.getAllBlogs();
        }
        if(key.equals(Constant.ALL_TOPICS)){
            return "";
        }
        if(key.equals(Constant.PERMISSONS_CONTROL)){
            return shiroConfigureService.getAll();
        }
        if(key.equals(Constant.ALL_USERS)){
            return userService.findAll();
        }
        if(key.equals(Constant.ALL_MODULES)){
            return moduleMapper.selectAll();
        }
        if(key.equals(Constant.ALL_ROLES)){
            return roleService.getAll();
        }

        return null;
    }


}
