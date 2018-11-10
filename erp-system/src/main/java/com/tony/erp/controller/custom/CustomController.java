package com.tony.erp.controller.custom;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Custom;
import com.tony.erp.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    /**
     * 获取所有客户信息
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAllCustoms")
    public String getAllCustoms(int pageSize, ModelMap modelMap){
        if(ObjectUtils.isEmpty(pageSize)){
            modelMap.addAttribute("customs",customService.getAllCustoms(1));
        }
        modelMap.addAttribute("customs",customService.getAllCustoms(pageSize));
        return "";
    }

    /**
     * 新增客户信息
     * @param
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addCus(Custom custom){
        if(ObjectUtils.isEmpty(custom)){
            return Constant.ARG_EXCEPTION;
        }
        return customService.addCustom(custom)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新客户信息
     * @param custom
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upCus(Custom custom){
        if(ObjectUtils.isEmpty(custom)){
            return Constant.ARG_EXCEPTION;
        }
        return customService.upCustom(custom)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除客户信息
     * @param c_id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delCus(String c_id){
        if(ObjectUtils.isEmpty(c_id)){
            return Constant.ARG_EXCEPTION;
        }
        return customService.delCustom(c_id)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

    /**
     * 根据主键获取客户信息
     * @param c_id
     * @return
     */
    @RequestMapping("/getCustom")
    @ResponseBody
    public Custom getCus(String c_id){
        if(ObjectUtils.isEmpty(c_id)){
            return null;
        }
        return customService.getCustom(c_id);
    }
}
