package com.tony.blog.service;

import com.tony.blog.dao.UserMapper;
import com.tony.blog.entity.User;
import com.tony.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Transactional
    public User findUserByName(String username){
        User u=userMapper.findUserByName(username);
        if(!ObjectUtils.isEmpty(u)){
            return u;
        }
        return null;
    }

    /**
     * 根据用户名查找用户权限信息
     * @param username
     * @return
     */
    @Transactional
    public User findUserProperties(String username){
        User u=userMapper.findUserName(username);
        if(!ObjectUtils.isEmpty(u)){
            return u;
        }
        return null;
    }


    /**
     * 保存用户
     * @param user
     * @return
     */
    @Transactional
    public int save(User user){
        user.setUid(UUIDUtils.GenerateKey());
        return userMapper.insert(user);
    }

    /**
     * 用户角色分配
     * @param map
     * @return
     */
    @Transactional
    public int insertUserRole(Map<String,Object> map) {
        return userMapper.insertUserRole(map);
    }


    @Transactional
    public List<User> findAll(){
        return userMapper.findAll();
    }

}
