package com.tony.erp.service;

import com.tony.erp.dao.RoleMapper;
import com.tony.erp.domain.Role;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 从数据库中取出所有role,并去除超级管理员
     * @return
     */
    @Transactional
    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }

    /**
     * 添加新的角色
     * @param roleName
     * @return
     */
    @Transactional
    public Role addRole(String roleName){
        if(ObjectUtils.isEmpty(roleName)){
            return null;
        }
        Role role=new Role();
        role.setRid(KeyGeneratorUtils.keyUUID());
        role.setRname(roleName);
        roleMapper.insert(role);
        return roleMapper.selectByPrimaryKey(role.getRid());
    }

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    @Transactional
    public int updateRole(Role role){
        return roleMapper.updateByPrimaryKeySelective(role);
    }

}
