package com.tony.blog.dao;

import com.tony.blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {

    int deleteByPrimaryKey(String id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> findBlogsByUid(String uid);

    List<Blog> findBlogsByTag(String tag);

    List<Blog> getAllBlogs();

    List<Blog> findBlogsByUserTag(Map<String,Object> map);


}