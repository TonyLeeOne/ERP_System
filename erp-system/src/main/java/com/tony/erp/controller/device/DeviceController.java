package com.tony.erp.controller.device;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Device;
import com.tony.erp.domain.Vendor;
import com.tony.erp.service.DeviceService;
import com.tony.erp.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.SAAJResult;
import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/getAllDevices")
    public String getAllDevices(int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("devices", deviceService.getAllDevices(1));
        return "/device/list";
    }


    @GetMapping("/getAllDevices/{pageNum}")
    public String getAllDevice(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("page", deviceService.getAllDevices(pageNum));
        return "/device/list";
    }

    /**
     * 编辑、新增页面
     *
     * @param deviceId
     * @param modelMap
     * @return
     */
    @RequestMapping("/edit")
    public String editDevice(@RequestParam(defaultValue = "", required = false) String deviceId,
                             ModelMap modelMap) {
        if (deviceId != null && !"".equals(deviceId)) {
            Device device = deviceService.selectByPrimaryKey(deviceId);
            modelMap.addAttribute("device", device);
        }
        List<Vendor> vendors = vendorService.getAll();
        modelMap.addAttribute("vendors", vendors);
        return "/device/edit";
    }

    /**
     * 保存数据
     *
     * @param deviceId
     * @param device
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addDevice(@RequestParam(defaultValue = "", required = false) String deviceId, Device device) {
        if (ObjectUtils.isEmpty(device) && StringUtils.isEmpty(device.getDeviceCode())) {
            return Constant.ARG_EXCEPTION;
        }
        Vendor vendor = vendorService.getSingleVendor(device.getDeviceVendor());
        device.setDeviceVendorTel(vendor.getVTel());

        //更新
        if (device.getDeviceId() != null && !"".equals(device.getDeviceId())) {
            return deviceService.upDevice(device) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
        }

        //新增
        if (!deviceService.checkDeviceCode(device.getDeviceCode())) {
            return deviceService.addDevice(device) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
        }

        return Constant.DEVICE_CODE_EXISTS;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delDevice(String deviceId) {
        return deviceService.delDevice(deviceId) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(@RequestBody String[] ids) {
        if (ids.length < 1) {
            return Constant.ARG_EXCEPTION;
        }
        return deviceService.batchDeleteByIds(ids) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }
}
