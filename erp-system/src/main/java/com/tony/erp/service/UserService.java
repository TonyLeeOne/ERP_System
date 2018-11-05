package com.tony.erp.service;

import com.tony.erp.dao.UserMapper;
import com.tony.erp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserPropertiesByUsername(String username){
        User user=userMapper.findPropertiesByUsername(username);
        if(!ObjectUtils.isEmpty(user)){
            return user;
        }
        return null;
    }
}
