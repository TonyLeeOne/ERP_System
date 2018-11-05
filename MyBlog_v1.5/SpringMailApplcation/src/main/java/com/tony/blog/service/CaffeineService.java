package com.tony.blog.service;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.blog.constant.Constant;
import com.tony.blog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaffeineService {

    @Autowired
    private LoadingCache<String,Object> cache;

    public List<Proverb> getProverbs(){
        return (List<Proverb>) cache.get(Constant.PROVERB_RANDOM);
    }

    public List<Blog> getAllBlogs(){
        return (List<Blog>) cache.get(Constant.BLOGS);
    }

    public List<ShiroConfigure> getAllPerms(){
        return (List<ShiroConfigure>) cache.get(Constant.PERMISSONS_CONTROL);
    }

    public List<User> getAllUsers(){
        return (List<User>) cache.get(Constant.ALL_USERS);
    }

    public List<Module> getAllModules(){
        return (List<Module>) cache.get(Constant.ALL_MODULES);
    }
    public List<Role> getAllRoles(){
        return (List<Role>) cache.get(Constant.ALL_ROLES);
    }
}
