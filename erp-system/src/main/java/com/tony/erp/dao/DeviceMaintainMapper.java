package com.tony.erp.dao;

import com.tony.erp.domain.DeviceMaintain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface DeviceMaintainMapper {
    int deleteByPrimaryKey(String hisId);

    int insert(DeviceMaintain record);

    int insertSelective(DeviceMaintain record);

    DeviceMaintain selectByPrimaryKey(String hisId);

    int updateByPrimaryKeySelective(DeviceMaintain record);

    int updateByPrimaryKey(DeviceMaintain record);

    List<DeviceMaintain> selectByDeviceCode(String deviceCode);

    List<DeviceMaintain> selectAll();

    List<DeviceMaintain> getAllDeviceMaintains();
    int batchDeleteByIds(@Param("ids") String[] ids);

}