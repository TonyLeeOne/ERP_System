package com.tony.erp.service;

import com.tony.erp.dao.DeviceMaintainMapper;
import com.tony.erp.domain.DeviceMaintain;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceMaintainService {

    @Autowired
    private DeviceMaintainMapper deviceMaintainMapper;

    @Transactional
    public int addDeviceMain(DeviceMaintain deviceMaintain) {
        deviceMaintain.setHisId(KeyGeneratorUtils.keyUUID());
        deviceMaintain.setHisDate(KeyGeneratorUtils.dateGenerator());
        return deviceMaintainMapper.insert(deviceMaintain);
    }

    @Transactional
    public int delDeviceMain(String hisId){
        return deviceMaintainMapper.deleteByPrimaryKey(hisId);
    }
    @Transactional
    public int update(DeviceMaintain deviceMaintain){
        return deviceMaintainMapper.updateByPrimaryKeySelective(deviceMaintain);
    }

    @Transactional
    public List<DeviceMaintain> getDeviceMain(String device_code){
        return deviceMaintainMapper.selectByDeviceCode(device_code);
    }

    @Transactional
    public List<DeviceMaintain> getAllDeviceMain(){
        return deviceMaintainMapper.selectAll();
    }
}
