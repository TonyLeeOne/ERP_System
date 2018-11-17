package com.tony.erp.service;

import com.tony.erp.dao.DeviceMaintainMapper;
import com.tony.erp.domain.DeviceMaintain;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceMaintainService {

    @Autowired
    private DeviceMaintainMapper deviceMaintainMapper;

    public int addDeviceMain(DeviceMaintain deviceMaintain) {
        deviceMaintain.setHisId(KeyGeneratorUtils.keyUUID());
        deviceMaintain.setHisDate(KeyGeneratorUtils.dateGenerator());
        return deviceMaintainMapper.insert(deviceMaintain);
    }

    public int delDeviceMain(String hisId){
        return deviceMaintainMapper.deleteByPrimaryKey(hisId);
    }
    public int update(DeviceMaintain deviceMaintain){
        return deviceMaintainMapper.updateByPrimaryKeySelective(deviceMaintain);
    }

    public List<DeviceMaintain> getDeviceMain(String deviceCode){
        return deviceMaintainMapper.selectByDeviceCode(deviceCode);
    }

    public List<DeviceMaintain> getAllDeviceMain(){
        return deviceMaintainMapper.selectAll();
    }
}
