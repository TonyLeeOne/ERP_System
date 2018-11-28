package com.tony.erp.dao;

import com.tony.erp.domain.ManPlan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface ManPlanMapper {

    int deleteByPrimaryKey(String mpId);

    int insert(ManPlan record);

    int insertSelective(ManPlan record);

    ManPlan selectByPrimaryKey(String mpId);

    int updateByPrimaryKeySelective(ManPlan record);

    int updateByPrimaryKey(ManPlan record);

    <T, K, V> List<T> find(Map<K, V> params);

    int getTotal();

}