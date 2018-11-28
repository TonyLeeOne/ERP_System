package com.tony.erp.dao;

import com.tony.erp.domain.UrlConfigure;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface UrlConfigureMapper {
    int deleteByPrimaryKey(String id);

    int insert(UrlConfigure record);

    int insertSelective(UrlConfigure record);

    UrlConfigure selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UrlConfigure record);

    int updateByPrimaryKey(UrlConfigure record);

    int insertAllUrls(@Param("list")List<UrlConfigure> list);

    int batchDelete(@Param("list")List<String> list);

    List<UrlConfigure> selectAll();
}