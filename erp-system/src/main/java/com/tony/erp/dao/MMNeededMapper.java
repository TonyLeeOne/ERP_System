package com.tony.erp.dao;

import com.tony.erp.domain.MMNeeded;
import com.tony.erp.domain.MMNeededWithBLOBs;

public interface MMNeededMapper {
    int deleteByPrimaryKey(String mmnId);

    int insert(MMNeededWithBLOBs record);

    int insertSelective(MMNeededWithBLOBs record);

    MMNeededWithBLOBs selectByPrimaryKey(String mmnId);

    int updateByPrimaryKeySelective(MMNeededWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MMNeededWithBLOBs record);

    int updateByPrimaryKey(MMNeeded record);
}