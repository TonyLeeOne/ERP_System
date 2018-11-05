package com.tony.blog.dao;

import com.tony.blog.entity.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TopicMapper {

    int deleteByPrimaryKey(String id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> selectByStatus(String status);

    <T,K,V> List<T> find(Map<K,V> paras);

    List<String> findTags();
}