package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.UserMapper;
import com.tony.erp.domain.User;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
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
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfileService profileService;

    /**
     * 根据id查找用户信息
     *
     * @param id
     * @return
     */
    public User getByPrimaryKey(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    public User getUserPropertiesByUsername(String username) {
        List<User> users = userMapper.findPropertiesByUsername(username);
        if (!ObjectUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 添加新用户
     *
     * @return
     */
    public User saveUser(User user) {
        user.setId(KeyGeneratorUtils.keyUUID());
        user.setStatus(Constant.STRING_ONE);
        userMapper.insertSelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }


    /**
     * update用户信息
     *
     * @return
     */
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    public PageHelperEntity getAllUsers(int pageSize) {
        PageHelper.startPage(pageSize, 10);
        List<User> users = userMapper.getAllUsers();
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(users);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        pageHelperEntity.setTotal(getTotal());
        pageHelperEntity.setPageNum(pageInfo.getPageNum());
        return pageHelperEntity;
    }

    /**
     * 查看用户名是否被注册
     *
     * @param uanme
     * @return
     */
    public boolean checkExists(String uanme) {
        return userMapper.selectByUname(uanme) > 0 ? true : false;
    }


    /**
     * 查看所有用户数量
     *
     * @return
     */
    public int getTotal() {
        return userMapper.selectByUname(null);
    }


    /**
     * 获取部门下有多少用户
     *
     * @param departId
     * @return
     */
    public int getTotalByDepartId(String departId) {
        return userMapper.selecTotalByDepartId(departId);
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    public int deleteByPrimaryKey(String uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    public int batchDeleteByIds(String[] ids) {
        return userMapper.batchDeleteByIds(ids);
    }

    /**
     * 获取所有用户名
     * @return
     */
    public List<String> selectAllUnames(){
        return userMapper.selectAllUnames();
    }
}
