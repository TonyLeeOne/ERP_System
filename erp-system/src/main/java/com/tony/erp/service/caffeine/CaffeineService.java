package com.tony.erp.service.caffeine;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.pagehelper.PageHelper;
import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Module;
import com.tony.erp.domain.Role;
import com.tony.erp.domain.UrlConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Service
public class CaffeineService {

    @Autowired
    private LoadingCache<String, Object> cache;

    /**
     * 从缓存中读取所有urls及其权限配置
     *
     * @return
     */
    public List<UrlConfigure> getAllUrls() {
        return (List<UrlConfigure>) cache.get(Constant.ALL_URLS);
    }

    /**
     * 从缓存中读取所有modules
     *
     * @return
     */
    public List<Module> getAllModules() {
        return (List<Module>) cache.get(Constant.ALL_MODULES);
    }

    /**
     * 从缓存中读取所有roles
     *
     * @return
     */
    public List<Role> getAllRoles() {
        return (List<Role>) cache.get(Constant.ALL_ROLES);
    }

    public int getUsersTotal() {
        return (int) cache.get(Constant.USER_COUNTS);
    }

    public int getOrdersTotal() {
        return (int) cache.get(Constant.ORDERS_COUNTS);
    }

    public int getManOrdersTotal() {
        return (int) cache.get(Constant.MAN_ORDERS);
    }

    public int getMPSTotal() {
        return (int) cache.get(Constant.MAN_PLANS);
    }

    public int getCustomsTotal() {
        return (int) cache.get(Constant.CUSTOM_COUNTS);
    }

    public int getProductsTotal() {
        return (int) cache.get(Constant.PRODUCT_COUNTS);
    }


}
