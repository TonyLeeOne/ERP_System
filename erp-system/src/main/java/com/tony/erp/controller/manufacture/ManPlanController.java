package com.tony.erp.controller.manufacture;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.ManPlan;
import com.tony.erp.service.ManPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String addManPlan(ManPlan manPlan){
        return manPlanService.addManPlan(manPlan)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upManPlan(ManPlan manPlan){
        return manPlanService.upManPlan(manPlan)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delManPlan(String mpid){
        return manPlanService.delManPlan(mpid)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


    @RequestMapping("/getAll")
    public String delManPlan(ModelMap modelMap){
        modelMap.addAttribute("plans",manPlanService.getAll(1));
        return "";
    }

    @RequestMapping("/getAll/{pageNum}")
    public String delManPlan(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("plans",manPlanService.getAll(pageNum));
        return "";
    }
}
