package com.tony.blog.service;

import com.tony.blog.dao.RoleAndModuleMapper;
import com.tony.blog.entity.RoleAndModule;
import com.tony.blog.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleAndModuleService {

    @Autowired
    private RoleAndModuleMapper roleAndModuleMapper;

    public int insertRoleAndModule(List<RoleAndModule> roleAndModule){
        return roleAndModuleMapper.insert(roleAndModule);
    }

    /**
     * 更新role的权限模块
     * @param request
     * @param rid
     * @return
     */
    public int changeModules(List<String> request,String rid){
        if(!StringUtils.isEmpty(rid)){
            List<String> origin=roleAndModuleMapper.findByRid(rid);
            if(!CollectionUtils.isEmpty(origin)&&!CollectionUtils.isEmpty(request)){
                List<String> delete=ListUtils.difference(origin,request);
                delete.forEach(mid->{
                    roleAndModuleMapper.delete(new RoleAndModule(rid,mid));
                });
            }
        }
        return 0;
    }

    public int delete(RoleAndModule roleAndModule){
        return roleAndModuleMapper.delete(roleAndModule);
    }

    public boolean checkExist(RoleAndModule roleAndModule){
        return roleAndModuleMapper.checkExist(roleAndModule)>0;
    }
}
