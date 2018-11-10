package com.tony.erp.dao;

import com.tony.erp.domain.ManOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface ManOrderMapper {
    int deleteByPrimaryKey(String moId);

    int insert(ManOrder record);

    int insertSelective(ManOrder record);

    ManOrder selectByPrimaryKey(String moId);

    int updateByPrimaryKeySelective(ManOrder record);

    int updateByPrimaryKey(ManOrder record);
}