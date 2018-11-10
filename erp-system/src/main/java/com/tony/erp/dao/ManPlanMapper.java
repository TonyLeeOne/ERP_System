package com.tony.erp.dao;

import com.tony.erp.domain.ManPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface ManPlanMapper {
    int deleteByPrimaryKey(String mpId);

    int insert(ManPlan record);

    int insertSelective(ManPlan record);

    ManPlan selectByPrimaryKey(String mpId);

    int updateByPrimaryKeySelective(ManPlan record);

    int updateByPrimaryKey(ManPlan record);
}