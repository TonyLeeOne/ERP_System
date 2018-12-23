package com.tony.erp.dao;

import com.tony.erp.domain.BOM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BOMMapper {

    int deleteByPrimaryKey(String bId);

    int insert(BOM record);

    int insertSelective(BOM record);

    BOM selectByPrimaryKey(String bId);

    BOM selectByPCode(@Param("bpCode") String bpCode);

    int updateByPrimaryKeySelective(BOM record);

    int updateByPrimaryKey(BOM record);

    List<BOM> find(@Param("bCode")String bCode);

    int checkExistByBCode(@Param("bCode")String bCode);
}