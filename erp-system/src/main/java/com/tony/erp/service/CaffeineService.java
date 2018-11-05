package com.tony.erp.service;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.erp.constant.Constant;
import com.tony.erp.domain.UrlConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaffeineService {

    @Autowired
    private LoadingCache<String,Object> cache;

    /**
     * 从缓存中读取所有urls及其权限配置
     * @return
     */
    public List<UrlConfigure> getAllUrls(){
        return (List<UrlConfigure>) cache.get(Constant.ALL_URLS);
    }

}
