package com.tony.blog.dao;

import com.tony.blog.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMapper {

    int deleteByPrimaryKey(String id);

    int insert(Profile record);

    int insertSelective(Profile record);

    Profile selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);
}