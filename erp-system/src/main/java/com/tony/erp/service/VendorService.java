package com.tony.erp.service;

import com.tony.erp.dao.VendorMapper;
import com.tony.erp.domain.Vendor;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorMapper vendorMapper;

    @Transactional
    public int addVendor(Vendor vendor) {
        vendor.setvId(KeyGeneratorUtils.keyUUID());
        vendor.setvStatus("1");
        return vendorMapper.insert(vendor);
    }

    @Transactional
    public int upVendor(Vendor vendor) {
        return vendorMapper.updateByPrimaryKeySelective(vendor);
    }

    @Transactional
    public List<Vendor> getAllVendors() {
        return vendorMapper.getAllVendors();
    }

    @Transactional
    public int delVendor(String v_id) {
        return vendorMapper.deleteByPrimaryKey(v_id);
    }

    @Transactional
    public Vendor getSingleVendor(String v_id) {
        return vendorMapper.selectByPrimaryKey(v_id);
    }
}
