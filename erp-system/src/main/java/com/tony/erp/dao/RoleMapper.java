package com.tony.erp.dao;

import com.tony.erp.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Repository
public interface RoleMapper {

    int deleteByPrimaryKey(String rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRoles();

    int batchDeleteByIds(@Param("ids") String[] ids);

}