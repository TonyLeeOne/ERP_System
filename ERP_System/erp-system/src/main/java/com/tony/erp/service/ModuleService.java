package com.tony.erp.service;

import com.tony.erp.dao.ModuleMapper;
import com.tony.erp.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
public class ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * 获取所有的权限信息
     * @return
     */
    public List<Module> getAllModules(){
        return moduleMapper.getAllModules();
    }

}
