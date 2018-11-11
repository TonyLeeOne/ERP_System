package com.tony.erp.service;

import com.tony.erp.dao.VendorMapper;
import com.tony.erp.domain.Vendor;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class VendorService {

    @Autowired
    private VendorMapper vendorMapper;

    public int addVendor(Vendor vendor) {
        vendor.setvId(KeyGeneratorUtils.keyUUID());
        vendor.setvStatus("1");
        return vendorMapper.insert(vendor);
    }

    public int upVendor(Vendor vendor) {
        return vendorMapper.updateByPrimaryKeySelective(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorMapper.getAllVendors();
    }

    public int delVendor(String vid) {
        return vendorMapper.deleteByPrimaryKey(vid);
    }

    public Vendor getSingleVendor(String vid) {
        return vendorMapper.selectByPrimaryKey(vid);
    }
}
