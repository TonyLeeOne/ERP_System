package com.tony.erp.configuration.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.erp.constant.Constant;
import com.tony.erp.service.*;
import com.tony.erp.service.material.MaterialService;
import com.tony.erp.service.product.ProductService;
import com.tony.erp.service.shiro.UrlConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Configuration
public class CaffeineConfiguration {

    @Autowired
    private UrlConfigureService urlConfigureService;

    @Autowired
    private ModuleService moduleService;


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ManPlanService manPlanService;

    @Autowired
    private ManOrderService manOrderService;

    @Autowired
    private CustomService customService;
    @Autowired
    private MaterialService materialService;

    /**
     * 初始化cacheLoader实例
     *
     * @return
     */
    CacheLoader<Object, Object> cacheLoader() {
        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
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
     *
     * @return
     */
    @Bean
    LoadingCache<String, Object> loadingCache() {
        LoadingCache<String, Object> loadingCache = Caffeine.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .maximumSize(10000)
                .build(key -> getFromDB(key));

        return loadingCache;
    }


    private Object getFromDB(String key) {
        if (Constant.ALL_URLS.equals(key)) {
            return urlConfigureService.getAllUrlsFromDB();
        }
        if (Constant.ALL_MODULES.equals(key)) {
            return moduleService.getAllModules();
        }
        if (Constant.ALL_ROLES.equals(key)) {
            return roleService.getAllRoles();
        }
        if (Constant.USER_COUNTS.equals(key)) {
            return userService.getTotal();
        }
        if (Constant.ORDERS_COUNTS.equals(key)) {
            return orderService.getTotal();
        }
        if (Constant.PRODUCT_COUNTS.equals(key)) {
            return productService.getTotal();
        }
        if (Constant.MAN_PLANS.equals(key)) {
            return manPlanService.getTotal();
        }
        if (Constant.MAN_ORDERS.equals(key)) {
            return manOrderService.getTotal();
        }
        if (Constant.CUSTOM_COUNTS.equals(key)) {
            return customService.getTotal();
        }
        if (Constant.ORDER_DATA_COLLECTION.equals(key)) {
            return orderService.dataCollection();
        }
        if (Constant.MATERIAL_DATA_COLLECTION.equals(key)) {
            return materialService.dataCollection();
        }
        if (Constant.PRODUCT_DATA_COLLECTION.equals(key)) {
            return productService.dataCollection();
        }
        return null;
    }

}
