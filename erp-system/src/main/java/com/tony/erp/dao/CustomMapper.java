package com.tony.erp.dao;

import com.tony.erp.domain.Custom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomMapper {

    int deleteByPrimaryKey(String customId);

    int insert(Custom record);

    int insertSelective(Custom record);

    Custom selectByPrimaryKey(String customId);

    int updateByPrimaryKeySelective(Custom record);

    int updateByPrimaryKey(Custom record);

    List<Custom> getAllCustoms();
}