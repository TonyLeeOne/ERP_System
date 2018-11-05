package com.tony.blog.dao;

import com.tony.blog.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findAll();

    int selectByRname(String rname);
}