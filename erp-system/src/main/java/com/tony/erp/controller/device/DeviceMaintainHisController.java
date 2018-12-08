package com.tony.erp.controller.device;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Device;
import com.tony.erp.domain.DeviceMaintain;
import com.tony.erp.domain.Vendor;
import com.tony.erp.service.DeviceMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deviceHis")
public class DeviceMaintainHisController {

    @Autowired
    DeviceMaintainService deviceMaintainService;

    @RequestMapping("getAllDeviceHis/{pageNum}")
    public String getAllDevicehis(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("deviceHis", deviceMaintainService.getAllDeviceMain(pageNum));
        return "/device_maintain_his/list";
    }


    /**
     * 编辑、新增页面
     *
     * @param hisId
     * @param modelMap
     * @return
     */
    @RequestMapping("/edit")
    public String editDevice(@RequestParam(defaultValue = "", required = false) String hisId,
                             ModelMap modelMap) {
        if (hisId != null && !"".equals(hisId)) {
            DeviceMaintain device = deviceMaintainService.selectByPrimaryKey(hisId);
            modelMap.addAttribute("device", device);
        }
        return "/device_maintain_his/edit";
    }

    /**
     * 保存数据
     *
     * @param hisId
     * @param deviceMaintain
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addDevice(@RequestParam(defaultValue = "", required = false) String hisId, DeviceMaintain deviceMaintain) {
        if (hisId != null && !"".equals(hisId)) {
            DeviceMaintain department1 = deviceMaintainService.selectByPrimaryKey(hisId);
//            department1.setHisDate(deviceMaintain.getHisDate());
            department1.setHisDeviceCode(deviceMaintain.getHisDeviceCode());
            department1.setHisOperator(deviceMaintain.getHisOperator());
            department1.setHisNote(deviceMaintain.getHisNote());
            department1.setHisResult(deviceMaintain.getHisResult());
            return deviceMaintainService.update(department1) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
        }
        if (ObjectUtils.isEmpty(deviceMaintain)) {
            return Constant.ARG_EXCEPTION;
        }

        return deviceMaintainService.addDeviceMain(deviceMaintain) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delDevice(String hisId) {
        return deviceMaintainService.delDeviceMain(hisId) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
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
        return deviceMaintainService.batchDeleteByIds(ids) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }
}
