package com.tony.erp.dao;

import com.tony.erp.domain.MaterialConsume;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialConsumeMapper {
    int deleteByPrimaryKey(String mcId);

    int insert(MaterialConsume record);

    int insertSelective(MaterialConsume record);

    MaterialConsume selectByPrimaryKey(String mcId);

    int updateByPrimaryKeySelective(MaterialConsume record);

    int updateByPrimaryKey(MaterialConsume record);
}