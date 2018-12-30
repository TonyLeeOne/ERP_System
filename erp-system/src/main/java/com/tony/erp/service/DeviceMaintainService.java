package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.dao.DeviceMaintainMapper;
import com.tony.erp.domain.DeviceMaintain;
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
public class DeviceMaintainService {

    @Autowired
    private DeviceMaintainMapper deviceMaintainMapper;

    public PageHelperEntity getAllDeviceMain(int pageNum,DeviceMaintain param) {
        Integer pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        List<DeviceMaintain> devices = deviceMaintainMapper.getAllDeviceMaintains(param);
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(devices);
        pageHelperEntity.setCurrentPage(pageNum);
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<>(devices);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(), pageSize));
        return pageHelperEntity;
    }

    public int addDeviceMain(DeviceMaintain deviceMaintain) {
        deviceMaintain.setHisId(KeyGeneratorUtils.keyUUID());
        deviceMaintain.setHisDate(KeyGeneratorUtils.dateGenerator());
        return deviceMaintainMapper.insert(deviceMaintain);
    }

    public int delDeviceMain(String hisId) {
        return deviceMaintainMapper.deleteByPrimaryKey(hisId);
    }

    public int update(DeviceMaintain deviceMaintain) {
        return deviceMaintainMapper.updateByPrimaryKeySelective(deviceMaintain);
    }

    public List<DeviceMaintain> getDeviceMain(String deviceCode) {
        return deviceMaintainMapper.selectByDeviceCode(deviceCode);
    }
    public DeviceMaintain selectByPrimaryKey(String vid) {
        return deviceMaintainMapper.selectByPrimaryKey(vid);
    }

    public int batchDeleteByIds(String[] ids) {
        return deviceMaintainMapper.batchDeleteByIds(ids);
    }

}
