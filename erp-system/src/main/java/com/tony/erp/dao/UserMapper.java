package com.tony.erp.dao;

import com.tony.erp.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findPropertiesByUsername(@Param("uname") String uname);
    List<User> getAllUsers();

    int selectByUname(String uname);

}