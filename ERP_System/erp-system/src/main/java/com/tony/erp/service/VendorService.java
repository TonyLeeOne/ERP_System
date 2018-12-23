package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.dao.VendorMapper;
import com.tony.erp.domain.Vendor;
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
public class VendorService {

    @Autowired
    private VendorMapper vendorMapper;

    public int addVendor(Vendor vendor) {
        vendor.setVId(KeyGeneratorUtils.keyUUID());
        return vendorMapper.insert(vendor);
    }

    public int upVendor(Vendor vendor) {
        return vendorMapper.updateByPrimaryKeySelective(vendor);
    }

    public PageHelperEntity getAllVendors(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Vendor> vendors = vendorMapper.getAllVendors();
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(vendors);
        PageInfo<Vendor> pageInfo = new PageInfo<>(vendors);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(), 10));
        return pageHelperEntity;
    }

    public int delVendor(String vid) {
        return vendorMapper.deleteByPrimaryKey(vid);
    }

    public Vendor getSingleVendor(String vid) {
        return vendorMapper.selectByPrimaryKey(vid);
    }

    public int batchDeleteByIds(String[] ids) {
        return vendorMapper.batchDeleteByIds(ids);
    }

    public List<Vendor> getAll() {
        return vendorMapper.getAllVendors();
    }
}
