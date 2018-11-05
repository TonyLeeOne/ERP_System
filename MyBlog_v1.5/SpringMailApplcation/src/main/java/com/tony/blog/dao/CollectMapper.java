package com.tony.blog.dao;

import com.tony.blog.entity.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {

    int deleteByPrimaryKey(String id);

    int insert(Collect record);

    int insertSelective(Collect record);

    int checkExist(Collect collect);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> findByUser(@Param("uid") String uid);
}