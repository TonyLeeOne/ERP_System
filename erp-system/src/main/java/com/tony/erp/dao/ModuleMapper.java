package com.tony.erp.dao;

import com.tony.erp.domain.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {

    int deleteByPrimaryKey(String mid);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String mid);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Module> getAllModules();
}