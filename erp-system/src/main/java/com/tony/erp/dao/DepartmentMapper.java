package com.tony.erp.dao;

import com.tony.erp.domain.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String did);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String did);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}