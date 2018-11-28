package com.tony.erp.dao;

import com.tony.erp.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Repository
public interface DepartmentMapper {

    int deleteByPrimaryKey(String dId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String dId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAll();
}