package com.tony.blog.dao;

import com.tony.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByName(String uname);

    User findUserName(String uname);

    //新增用户角色
    int insertUserRole(@Param("map") Map<String,Object> map);

    List<User> findAll();
}