package com.tony.erp.dao;

import com.tony.erp.domain.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Repository
public interface UserRoleMapper {

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int selectByRid(String rid);

    int selectByRids(@Param("rids") String[] rids);

    int delete(String rid);
}