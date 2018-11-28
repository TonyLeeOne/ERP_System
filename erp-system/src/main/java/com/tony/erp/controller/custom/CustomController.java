package com.tony.erp.controller.custom;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Custom;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    /**
     * 获取所有客户信息
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAllCustoms")
    public String getAllCustoms(ModelMap modelMap) {
        modelMap.addAttribute("customs", customService.getAllCustoms(1));
        return "/custom/list";
    }

    /**
     * 分页查询客户信息
     *
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAllCustoms/{pageSize}")
    public String getAllCustom(@PathVariable int pageSize, ModelMap modelMap) {
        modelMap.addAttribute("customs", customService.getAllCustoms(pageSize));
        return "";
    }


    /**
     * 新增客户信息
     * @param
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addCus(Custom custom) {
        if (ObjectUtils.isEmpty(custom)) {
            return Constant.ARG_EXCEPTION;
        }
        return customService.addCustom(custom) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    /**
     * 新增/编辑客户信息页面
     * @param customId
     * @param modelMap
     * @return
     */
    @GetMapping("/edit")
    public String editCustom(@RequestParam(defaultValue = "", required = false) String customId, ModelMap modelMap) {
        if (customId != null && !"".equals(customId)) {
            Custom custom = customService.getCustom(customId);
            modelMap.addAttribute("custom", custom);
        }
        return "/custom/edit";
    }

    /**
     * 更新客户信息
     *
     * @param custom
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upCus(Custom custom) {
        if (ObjectUtils.isEmpty(custom)) {
            return Constant.ARG_EXCEPTION;
        }
        return customService.upCustom(custom) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除客户信息
     *
     * @param cid
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delCus(String cid) {
        if (ObjectUtils.isEmpty(cid)) {
            return Constant.ARG_EXCEPTION;
        }
        return customService.delCustom(cid) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }

    /**
     * 根据主键获取客户信息
     *
     * @param cid
     * @return
     */
    @RequestMapping("/getCustom")
    @ResponseBody
    public Custom getCus(String cid) {
        if (ObjectUtils.isEmpty(cid)) {
            return null;
        }
        return customService.getCustom(cid);
    }


    /**
     * 获取所有客户名和编号
     * @return
     */
    @RequestMapping("/getAllCusNames")
    @ResponseBody
    public List<String> getCustoms() {
        return customService.getCustoms();
    }


    /**
     * 根据客户编号获取客户信息
     * @return
     */
    @RequestMapping("/getCustomByCode")
    @ResponseBody
    public Custom getCustomByCode(String customCode) {
        return customService.getCustom(customCode);
    }
}
