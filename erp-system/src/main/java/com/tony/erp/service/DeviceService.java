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
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Transactional
    public List<Device> getAllDevices(int pageNum){
        PageHelper.startPage(pageNum,10);
        return deviceMapper.getAllDevices();
    }

    @Transactional
    public int addDevice(Device device){
        device.setDeviceId(KeyGeneratorUtils.keyUUID());
        device.setDeviceStatus("1");
        return deviceMapper.insert(device);
    }
    @Transactional
    public int upDevice(Device device){
        return deviceMapper.updateByPrimaryKeySelective(device);
    }
    @Transactional
    public int delDevice(String did){
        return deviceMapper.deleteByPrimaryKey(did);
    }
    @Transactional
    public boolean checkDeviceCode(String device_code){
        return deviceMapper.checkDeviceCode(device_code)>0?true:false;
    }


}
