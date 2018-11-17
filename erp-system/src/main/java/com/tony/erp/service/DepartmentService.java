package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.dao.DepartmentMapper;
import com.tony.erp.domain.Department;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
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

    public PageHelperEntity getAllDeparts(int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Department> departments= departmentMapper.selectAll();
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(departments);
        PageInfo<Department> pageInfo=new PageInfo<>(departments);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(),10));
        return pageHelperEntity;
    }

    public Department getDepart(String did) {
        return departmentMapper.selectByPrimaryKey(did);
    }


}
