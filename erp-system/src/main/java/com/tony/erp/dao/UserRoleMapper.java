package com.tony.erp.dao;

import com.tony.erp.domain.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int selectByRid(String rid);

    int delete(String rid);
}