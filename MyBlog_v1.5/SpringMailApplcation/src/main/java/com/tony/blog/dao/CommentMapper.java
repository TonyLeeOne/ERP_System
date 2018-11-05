package com.tony.blog.dao;

import com.tony.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentMapper {

    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    <T, K, V> List<T> find(Map<K, V> params);

    List<Comment> findByTid(@Param("tid") String tid);

    int deleteByTid(String tid);
}