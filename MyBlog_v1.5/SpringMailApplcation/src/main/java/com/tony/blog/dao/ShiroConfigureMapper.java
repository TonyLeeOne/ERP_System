package com.tony.blog.dao;

import com.tony.blog.entity.ShiroConfigure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiroConfigureMapper {

    int deleteByPrimaryKey(String id);

    int insert(ShiroConfigure record);

    int insertSelective(ShiroConfigure record);

    ShiroConfigure selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShiroConfigure record);

    int updateByPrimaryKey(ShiroConfigure record);

    List<ShiroConfigure> findAll();

    int deleteAll();
}