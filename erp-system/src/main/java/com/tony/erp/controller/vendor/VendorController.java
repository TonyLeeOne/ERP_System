package com.tony.erp.controller.vendor;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.User;
import com.tony.erp.domain.Vendor;
import com.tony.erp.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @RequestMapping("/getAllVendors")
    public String getAllVendors(ModelMap modelMap){
        modelMap.addAttribute("vendors",vendorService.getAllVendors(1));
        return "/vendor/list";
    }

    /**
     * 编辑、新增页面
     * @param vId
     * @param modelMap
     * @return
     */
    @RequestMapping("/edit")
    public String editVendor(@RequestParam(defaultValue = "",required = false) String vId,
                             ModelMap modelMap){
        if (vId != null && !"".equals(vId)) {
            Vendor vendor = vendorService.getSingleVendor(vId);
            modelMap.addAttribute("vendor", vendor);
        }
        return "/vendor/edit";
    }

    @RequestMapping("/getAllVendors/{pageNum}")
    public String getAllVendor(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("vendors",vendorService.getAllVendors(pageNum));
        return "";
    }

    /**
     * ajax 新增、更新接口
     * @param vendor
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addVendor(Vendor vendor){
        if(ObjectUtils.isEmpty(vendor)){
            return Constant.ARG_EXCEPTION;
        }
        return vendorService.addVendor(vendor)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    @PostMapping("/update")
    @ResponseBody
    public String upVendor(Vendor vendor){
        if(ObjectUtils.isEmpty(vendor)){
            return Constant.ARG_EXCEPTION;
        }
        return vendorService.addVendor(vendor)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除
     * @param vid
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delVendor(String vid){
        if(StringUtils.isEmpty(vid)){
            return Constant.ARG_EXCEPTION;
        }
        return vendorService.delVendor(vid)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

    @PostMapping("/getVendor")
    @ResponseBody
    public Vendor getVendor(String vid){
        if(!StringUtils.isEmpty(vid)){
            return vendorService.getSingleVendor(vid);
        }
        return null;
    }




}
