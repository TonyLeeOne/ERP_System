package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.UserMapper;
import com.tony.erp.domain.Profile;
import com.tony.erp.domain.User;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProfileService profileService;

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    public User getUserPropertiesByUsername(String username){
        List<User> users=userMapper.findPropertiesByUsername(username);
        if(!ObjectUtils.isEmpty(users)){
            return users.get(0);
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


    /**
     * 获取所有用户信息
     * @return
     */
    public List<User> getAllUsers(int pageSize){
        PageHelper.startPage(pageSize,10);
        return userMapper.findPropertiesByUsername(null);
    }

    /**
     * 查看用户名是否被注册
     * @param uanme
     * @return
     */
    public boolean checkExists(String uanme){
        return userMapper.selectByUname(uanme)>0?true:false;
    }


}
