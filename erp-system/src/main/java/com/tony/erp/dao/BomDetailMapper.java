package com.tony.erp.dao;

import com.tony.erp.domain.BomDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BomDetailMapper {

    int deleteByPrimaryKey(String bdId);

    int insert(BomDetail record);

    int insertSelective(BomDetail record);

    BomDetail selectByPrimaryKey(String bdId);

    int updateByPrimaryKeySelective(BomDetail record);

    int updateByPrimaryKey(BomDetail record);

    List<BomDetail> find(@Param("bCode") String bCode);

}