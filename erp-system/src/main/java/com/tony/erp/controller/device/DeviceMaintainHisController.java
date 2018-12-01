package com.tony.erp.controller.device;

import com.tony.erp.service.DeviceMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deviceHis")
public class DeviceMaintainHisController {

    @Autowired
    DeviceMaintainService deviceMaintainService;

    @RequestMapping("getAllDeviceHis/{pageNum}")
    public String getAllDevicehis(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("deviceHis", deviceMaintainService.getAllDeviceMain());
        return "/device_maintain_his/list";
    }

}
