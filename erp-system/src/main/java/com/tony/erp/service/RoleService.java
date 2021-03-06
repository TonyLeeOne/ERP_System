package com.tony.erp.service;

import com.tony.erp.dao.RoleMapper;
import com.tony.erp.domain.Role;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 从数据库中取出所有role,并去除超级管理员
     *
     * @return
     */
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Role selectByPrimaryKey(String rid) {
        return roleMapper.selectByPrimaryKey(rid);
    }

    /**
     * 添加新的角色
     *
     * @param roleName
     * @return
     */
    public Role addRole(String roleName) {
        if (ObjectUtils.isEmpty(roleName)) {
            return null;
        }
        Role role = new Role();
        role.setRid(KeyGeneratorUtils.keyUUID());
        role.setRname(roleName);
        roleMapper.insert(role);
        return roleMapper.selectByPrimaryKey(role.getRid());
    }

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public int batchDeleteByRid(String[] rids) {
        return roleMapper.batchDeleteByRid(rids);
    }
}
