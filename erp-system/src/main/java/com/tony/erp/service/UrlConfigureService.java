package com.tony.erp.service;

import com.tony.erp.dao.UrlConfigureMapper;
import com.tony.erp.domain.UrlConfigure;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlConfigureService {

    @Autowired
    private UrlConfigureMapper mapper;

    @Autowired
    private UrlService service;

    /**
     * 获取项目中所有url，并保存到数据库中，默认权限为anon
     * @return
     */
    public List<UrlConfigure> insertAllUrls(){
        List<String> urls=service.getDifference();
        List<UrlConfigure> configures=new ArrayList<>();

        if(!CollectionUtils.isEmpty(urls)) {
            urls.forEach(str -> {
                if (!str.equals("/error")) {
                    UrlConfigure configure = new UrlConfigure(KeyGeneratorUtils.keyUUID(), str, "anon");
                    configures.add(configure);
                }
            });

                 mapper.insertAllUrls(configures);
        }

        return this.getAllUrlsFromDB();
    }


    /**
     * 从数据库中读取所有url的权限集合
     * @return
     */
    public List<UrlConfigure> getAllUrlsFromDB(){
        return mapper.selectAll();

    }

    /**
     * 根据主键更新urlConfigure的值
     * @param urlId
     * @param authority
     * @return
     */
    public int updateUrlConfigure(String urlId,String authority){
        UrlConfigure configure=new UrlConfigure();
        configure.setId(urlId);
        configure.setAuthority(authority);
        return mapper.updateByPrimaryKeySelective(configure);
    }


}
