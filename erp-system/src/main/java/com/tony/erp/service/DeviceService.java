package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.DeviceMapper;
import com.tony.erp.domain.Device;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public List<Device> getAllDevices(int pageNum){
        PageHelper.startPage(pageNum,10);
        return deviceMapper.getAllDevices();
    }

    public int addDevice(Device device){
        device.setDeviceId(KeyGeneratorUtils.keyUUID());
        device.setDeviceStatus("1");
        return deviceMapper.insert(device);
    }
    public int upDevice(Device device){
        return deviceMapper.updateByPrimaryKeySelective(device);
    }
    public int delDevice(String did){
        return deviceMapper.deleteByPrimaryKey(did);
    }
    public boolean checkDeviceCode(String deviceCode){
        return deviceMapper.checkDeviceCode(deviceCode)>0?true:false;
    }


}
