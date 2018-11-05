package com.tony.blog.service;

import com.tony.blog.dao.ShiroConfigureMapper;
import com.tony.blog.entity.ShiroConfigure;
import com.tony.blog.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class ShiroConfigureService {

    @Autowired
    private ShiroConfigureMapper mapper;

    @Autowired
    private UrlService service;

    @Autowired
    ShiroConfigureService shiroConfigureService;

    public int insertShiroConfigure(){
        List<ShiroConfigure> exists=shiroConfigureService.getAll();
        List<String> existUrls=new ArrayList<>();
        if(!CollectionUtils.isEmpty(exists)) {
            exists.forEach(str -> {
                existUrls.add(str.getUrl());
            });
        }
        List<String> urls=service.getAllUrls();
        List<String> insert=ListUtils.difference(urls,existUrls);

        if(!CollectionUtils.isEmpty(insert)){
            insert.stream().forEach(str->{
                ShiroConfigure configure=new ShiroConfigure();
                configure.setId(UUID.randomUUID().toString());
                configure.setUrl(str);
                configure.setAuthority("anon");
                mapper.insert(configure);
                log.info("新增的的URL是[{}]"+configure.toString());
            });
            return 1;
        }
        return 0;
    }

    public int defaultShiroConfigure(){
        int i=this.deleteAll();
        System.out.println(i);
        List<String> urls=service.getAllUrls();
        if(!CollectionUtils.isEmpty(urls)){
            urls.stream().forEach(str->{
                ShiroConfigure configure=new ShiroConfigure();
                configure.setId(UUID.randomUUID().toString());
                configure.setUrl(str);
                configure.setAuthority("anon");
                mapper.insert(configure);
            });
            log.info("初始化完成，共"+urls.size()+"条数据");
            return 1;
        }
        return 0;
    }

    public List<ShiroConfigure> getAll(){
        List<ShiroConfigure> list=mapper.findAll();
        if(!CollectionUtils.isEmpty(list)){
            return list;
        }
        return null;
    }

    public int updateShiro(ShiroConfigure shiroConfigure){
        return mapper.updateByPrimaryKey(shiroConfigure);
    }

    public ShiroConfigure selectById(String id){
        ShiroConfigure configure=mapper.selectByPrimaryKey(id);
        if(ObjectUtils.isEmpty(configure)){
            return null;
        }
        return configure;
    }


    public int deleteAll(){
        return mapper.deleteAll();
    }


}
