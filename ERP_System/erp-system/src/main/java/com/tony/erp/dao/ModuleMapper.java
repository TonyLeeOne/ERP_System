package com.tony.erp.dao;

import com.tony.erp.domain.Module;

import java.util.List;

public interface ModuleMapper {
    int deleteByPrimaryKey(String mid);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String mid);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Module> getAllModules();
}