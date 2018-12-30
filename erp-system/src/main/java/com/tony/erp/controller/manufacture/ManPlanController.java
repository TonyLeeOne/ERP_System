package com.tony.erp.controller.manufacture;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.ManPlan;
import com.tony.erp.service.ManPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tony.erp.constant.Constant.MPSN_EXISTS;

/**
 * @author jli2
 * @date 11/12/2018 10:34 AM
 **/
@Controller
@RequestMapping("/manPlan")
public class ManPlanController {

    @Autowired
    private ManPlanService manPlanService;

    @RequestMapping("/add")
    @ResponseBody
    public String addManPlan(ManPlan manPlan) {
        if (manPlanService.checkMpExist(manPlan.getMpSn())) {
            return MPSN_EXISTS;
        }
        return manPlanService.addManPlan(manPlan) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upManPlan(ManPlan manPlan) {
        return manPlanService.upManPlan(manPlan) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delManPlan(String mpid) {
        return manPlanService.delManPlan(mpid) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }


    @RequestMapping("/batchDeleteByMpSn")
    @ResponseBody
    public String batchDelManPlan(String mpSn) {
        String[] mpSns = mpSn.split(",");
        return manPlanService.batchDeleteByMpSn(mpSns) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }


    @RequestMapping("/getAll")
    public String delManPlan(ModelMap modelMap) {
        modelMap.addAttribute("plans", manPlanService.getAll(1, null));
        return "/mp/list";
    }

    @RequestMapping("/getAll/{pageNum}")
    public String delManPlan(@PathVariable int pageNum,
                             String mpStatus,
                             String mpOrderId,
                             String mpSn,
                             String mpProCode,
                             String mpStartDate,
                             String mpEndDate,
                             ModelMap modelMap) {
        Map<String, String> param = new HashMap<>();
        param.put("mpStatus", mpStatus);
        param.put("mpOrderId", mpOrderId);
        param.put("mpSn", mpSn);
        param.put("mpProCode", mpProCode);
        param.put("mpStartDate", mpStartDate);
        param.put("mpEndDate", mpEndDate);
        modelMap.addAttribute("mp", param);
        modelMap.addAttribute("page", manPlanService.getAll(pageNum, param));
        return "/mp/list";
    }


    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "mpSn", required = false) String mpSn, ModelMap modelMap) {
        if (!StringUtils.isEmpty(mpSn)) {
            modelMap.addAttribute("plan", manPlanService.getManPlanByMpSn(mpSn));
        }
        return "/mp/edit";
    }

    @RequestMapping("/getAllMpsns")
    @ResponseBody
    public List<String> getAllMpSns() {
        return manPlanService.selectMpSns();
    }

}
