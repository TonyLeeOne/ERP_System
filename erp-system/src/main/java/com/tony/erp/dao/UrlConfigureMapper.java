package com.tony.erp.dao;

import com.tony.erp.domain.UrlConfigure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlConfigureMapper {

    int deleteByPrimaryKey(String id);

    int insert(UrlConfigure record);

    int insertSelective(UrlConfigure record);

    UrlConfigure selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UrlConfigure record);

    int updateByPrimaryKey(UrlConfigure record);

    int insertAllUrls(List<UrlConfigure> list);

    List<UrlConfigure> selectAll();
}