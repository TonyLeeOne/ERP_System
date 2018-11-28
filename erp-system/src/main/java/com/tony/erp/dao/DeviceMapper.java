package com.tony.erp.dao;

import com.tony.erp.domain.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface DeviceMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> getAllDevices();

    int checkDeviceCode(@Param("deviceCode")String deviceCode);
}