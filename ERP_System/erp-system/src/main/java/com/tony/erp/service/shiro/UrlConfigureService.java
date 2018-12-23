package com.tony.erp.service.shiro;

import com.tony.erp.dao.UrlConfigureMapper;
import com.tony.erp.domain.UrlConfigure;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UrlConfigureService {

    @Autowired
    private UrlConfigureMapper mapper;

    @Autowired
    private UrlService service;


    private final String ERROR_PATH="/error";
    /**
     * 获取项目中所有url(全量)，并保存到数据库中，默认权限为anon
     *
     * @return
     */
    public int insertAllUrls() {
        Map<String, List<String>> urls = service.getDifference();
        List<UrlConfigure> inserts = new ArrayList<>();
        int res = 0;
        List<String> insert = urls.get("insert");
        List<String> delete = urls.get("delete");
        List<String> all = urls.get("all");
        if (CollectionUtils.isEmpty(all)) {
            if (!CollectionUtils.isEmpty(insert)) {
                insert.forEach(url -> {
                    if (!ERROR_PATH.equals(url)) {
                        UrlConfigure configure = new UrlConfigure(KeyGeneratorUtils.keyUUID(), url, "anon");
                        inserts.add(configure);
                    }
                });
                if (!CollectionUtils.isEmpty(inserts)) {
                    res = mapper.insertAllUrls(inserts);
                }
            }
            if (!CollectionUtils.isEmpty(delete)) {
                res += mapper.batchDelete(delete);
            }
            return res;
        } else {
            all.forEach(url -> {
                if (!ERROR_PATH.equals(url)) {
                    UrlConfigure configure = new UrlConfigure(KeyGeneratorUtils.keyUUID(), url, "anon");
                    inserts.add(configure);
                }
            });
            if (!CollectionUtils.isEmpty(inserts)) {
                res = mapper.insertAllUrls(inserts);
                return res;
            }
        }
        return -1;
    }
    /**
     * 从数据库中读取所有url的权限集合
     *
     * @return
     */
    public List<UrlConfigure> getAllUrlsFromDB() {
        return mapper.selectAll();

    }

    /**
     * 根据主键更新urlConfigure的值
     *
     * @param urlId
     * @param authority
     * @return
     */
    public int updateUrlConfigure(String urlId, String authority) {
        UrlConfigure configure = new UrlConfigure();
        configure.setId(urlId);
        configure.setAuthority(authority);
        return mapper.updateByPrimaryKeySelective(configure);
    }


}
