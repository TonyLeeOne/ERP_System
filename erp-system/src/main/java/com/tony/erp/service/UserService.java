package com.tony.erp.service;

import com.tony.erp.dao.UserMapper;
import com.tony.erp.domain.User;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    public User getUserPropertiesByUsername(String username){
        User user=userMapper.findPropertiesByUsername(username);
        if(!ObjectUtils.isEmpty(user)){
            return user;
        }
        return null;
    }

    /**
     * 添加新用户
     * @return
     */
    public User saveUser(User user){
        user.setId(KeyGeneratorUtils.keyUUID());
        user.setStatus("1");
        userMapper.insertSelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }


    /**
     * update用户信息
     * @return
     */
    public int updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }


}
