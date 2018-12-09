package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.dao.DeviceMapper;
import com.tony.erp.domain.Device;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public PageHelperEntity getAllDevices(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Device> devices = deviceMapper.getAllDevices();
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(devices);
        pageHelperEntity.setCurrentPage(pageNum);
        PageInfo<Device> pageInfo = new PageInfo<>(devices);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(), 10));
        return pageHelperEntity;
    }

    public int addDevice(Device device) {
        device.setDeviceId(KeyGeneratorUtils.keyUUID());
        return deviceMapper.insert(device);
    }

    public int upDevice(Device device) {
        return deviceMapper.updateByPrimaryKeySelective(device);
    }

    public int delDevice(String did) {
        return deviceMapper.deleteByPrimaryKey(did);
    }

    public boolean checkDeviceCode(String deviceCode) {
        return deviceMapper.checkDeviceCode(deviceCode) > 0 ? true : false;
    }

    public Device selectByPrimaryKey(String vid) {
        return deviceMapper.selectByPrimaryKey(vid);
    }

    public int batchDeleteByIds(String[] ids) {
        return deviceMapper.batchDeleteByIds(ids);
    }
}
