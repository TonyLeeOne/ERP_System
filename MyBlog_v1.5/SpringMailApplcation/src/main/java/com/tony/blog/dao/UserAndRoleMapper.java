package com.tony.blog.dao;

import com.tony.blog.entity.UserAndRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAndRoleMapper {

    int insert(UserAndRole record);

    int insertSelective(UserAndRole record);

    int update(UserAndRole userAndRole);

    int findByRid(String rid);
}