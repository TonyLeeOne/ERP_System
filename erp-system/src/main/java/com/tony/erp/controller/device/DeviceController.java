package com.tony.erp.controller.device;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Device;
import com.tony.erp.domain.Vendor;
import com.tony.erp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.SAAJResult;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getAllDevices")
    public String getAllDevices(int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("devices", deviceService.getAllDevices(1));
        return "/device/list";
    }


    @GetMapping("/getAllDevices/{pageNum}")
    public String getAllDevice(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("devices", deviceService.getAllDevices(pageNum));
        return "";
    }

    @RequestMapping("/edit")
    public String editDevice(@RequestParam(defaultValue = "",required = false) String vId,
                             ModelMap modelMap){
        if (vId != null && !"".equals(vId)) {
            Device device = deviceService.selectByPrimaryKey(vId);
            modelMap.addAttribute("device", device);
        }
        return "/list/edit";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addDevice(Device device) {
        if (ObjectUtils.isEmpty(device) && StringUtils.isEmpty(device.getDeviceCode())) {
            return Constant.ARG_EXCEPTION;
        }
        if (!deviceService.checkDeviceCode(device.getDeviceCode())) {
            return deviceService.addDevice(device) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
        }
        return Constant.DEVICE_CODE_EXISTS;
    }

    @PostMapping("/update")
    @ResponseBody
    public String upDevice(Device device) {
        return deviceService.upDevice(device) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delDevice(String did) {
        return deviceService.delDevice(did) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }
}
