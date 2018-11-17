package com.tony.erp.dao;

import com.tony.erp.domain.MaterialConsume;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface MaterialConsumeMapper {
    int deleteByPrimaryKey(String mcId);

    int insert(MaterialConsume record);

    int insertSelective(MaterialConsume record);

    MaterialConsume selectByPrimaryKey(String mcId);

    int updateByPrimaryKeySelective(MaterialConsume record);

    int updateByPrimaryKey(MaterialConsume record);

    List<MaterialConsume> selectByMcMSn(String msn);

    List<MaterialConsume> selectAll();

    int countByMcMSn(@Param("mcMsn") String mcMsn);


}