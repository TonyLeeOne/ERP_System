package com.tony.blog.service;

import com.tony.blog.dao.UserAndRoleMapper;
import com.tony.blog.entity.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAndRoleService {

    @Autowired
    private UserAndRoleMapper userAndRoleMapper;

    public int update(UserAndRole role){
        return userAndRoleMapper.update(role);
    }

    public boolean checkRoleAndUserExist(String rid){
        return userAndRoleMapper.findByRid(rid)>0;
    }
}
