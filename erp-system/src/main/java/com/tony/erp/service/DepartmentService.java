package com.tony.erp.service;

import com.tony.erp.dao.DepartmentMapper;
import com.tony.erp.domain.Department;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    public int addDepart(Department department) {
        department.setdId(KeyGeneratorUtils.keyUUID());
        return departmentMapper.insertSelective(department);
    }

    @Transactional
    public int updateDepart(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Transactional
    public int delDepart(String did) {
        return departmentMapper.deleteByPrimaryKey(did);
    }

    @Transactional
    public List<Department> getAllDeparts() {
        return departmentMapper.selectAll();
    }

    @Transactional
    public Department getDepart(String did) {
        return departmentMapper.selectByPrimaryKey(did);
    }


}
