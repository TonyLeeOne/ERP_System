package com.tony.erp.service;

import com.tony.erp.dao.UserRoleMapper;
import com.tony.erp.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public boolean insertUserRole(String uid, String rid) {
        if (StringUtils.isEmpty(rid)||StringUtils.isEmpty(uid)) {
            return false;
        }
        UserRole userRole = new UserRole(uid, rid);
        return userRoleMapper.insert(userRole) > 0 ? true : false;
    }

    /**
     * 根据rid获取当前此角色的使用统计
     *
     * @param rid
     * @return
     */
    public boolean check(String rid) {
        return userRoleMapper.selectByRid(rid) > 0 ? true : false;
    }

    /**
     * 根据rids获取角色的使用统计
     *
     * @param rids
     * @return
     */
    public boolean checks(String[] rids) {
        return userRoleMapper.selectByRids(rids) > 0 ? true : false;
    }

    /**
     * 根据rid删除角色
     *
     * @param uid
     * @return
     */
    public int deleteByUid(String uid) {
        return userRoleMapper.delete(uid);
    }

    public int deleteByRid(String rid) {
        return userRoleMapper.deleteByRid(rid);
    }



}