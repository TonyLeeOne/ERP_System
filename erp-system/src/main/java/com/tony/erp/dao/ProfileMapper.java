package com.tony.erp.dao;

import com.tony.erp.domain.Profile;
import org.springframework.stereotype.Repository;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface ProfileMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Profile record);

    int insertSelective(Profile record);

    Profile selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);
}