package com.tony.erp.service;

import com.tony.erp.dao.DepartmentMapper;
import com.tony.erp.domain.Department;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public int addDepart(Department department) {
        department.setdId(KeyGeneratorUtils.keyUUID());
        return departmentMapper.insertSelective(department);
    }

    public int updateDepart(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    public int delDepart(String did) {
        return departmentMapper.deleteByPrimaryKey(did);
    }

    public List<Department> getAllDeparts() {
        return departmentMapper.selectAll();
    }

    public Department getDepart(String did) {
        return departmentMapper.selectByPrimaryKey(did);
    }


}
