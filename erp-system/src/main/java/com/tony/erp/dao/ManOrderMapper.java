package com.tony.erp.dao;

import com.tony.erp.domain.ManOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface ManOrderMapper {

    int deleteByPrimaryKey(String moId);

    int insert(ManOrder record);

    int insertSelective(ManOrder record);

    ManOrder selectByPrimaryKey(String moId);

    int updateByPrimaryKeySelective(ManOrder record);

    int updateByPrimaryKey(ManOrder record);

    List<String> selectStatusByMpSn(@Param("moMpSn")String moMpSn);

    int getTotal();

    List<ManOrder> getAllManOrders();

    List<ManOrder> selectByMpSn(@Param("moMpSn")String moMpSn);

    ManOrder selectByMoSn(@Param("moSn")String MoSn);

    List<String> selectAllMoSn(@Param("moStatus")String moStatus);

}