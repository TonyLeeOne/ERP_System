package com.tony.blog.service;

import com.tony.blog.dao.RoleMapper;
import com.tony.blog.entity.Role;
import com.tony.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAll(){
        return roleMapper.findAll();
    }

    public boolean checkRoleExist(String rname){
        return roleMapper.selectByRname(rname)>0;
    }

    public Role insertRole(Role role){
        role.setRid(UUIDUtils.GenerateKey());
        roleMapper.insert(role);
        return getRole(role);
    }


    private Role getRole(Role role){
        return roleMapper.selectByPrimaryKey(role.getRid());
    }


    public int deleteRole(String rid){
        return roleMapper.deleteByPrimaryKey(rid);
    }



}
