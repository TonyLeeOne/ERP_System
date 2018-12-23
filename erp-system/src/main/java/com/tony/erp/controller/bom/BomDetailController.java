package com.tony.erp.controller.bom;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tony.erp.domain.BomDetail;
import com.tony.erp.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.tony.erp.constant.Constant.*;

/**
 * @author jli2
 * @date 12/12/2018 5:33 PM
 **/
@Controller
@RequestMapping("/detail")
public class BomDetailController {

    @Autowired
    private DetailService service;

    @RequestMapping("/getAll")
    public String selectByBCode(@RequestParam("bCode")String bCode, ModelMap modelMap){
        if(!StringUtils.isEmpty(bCode)){
            modelMap.addAttribute("details",service.selectByBCode(bCode));
            modelMap.addAttribute("bCode",bCode);
        }
        return "/detail/list";
    }

    @RequestMapping("/edit/{bCode}")
    public String edit(@RequestParam(defaultValue = "",required = false)String bdId, @PathVariable String bCode, ModelMap modelMap){
        if(!StringUtils.isEmpty(bdId)){
            modelMap.addAttribute("detail",service.selectByPrimaryKey(bdId));
            modelMap.addAttribute("bCode",bCode);
        }
        return "/detail/edit";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(BomDetail bomDetail){
        return service.add(bomDetail)>0?DATA_ADD_SUCCESS:DATA_ADD_FAILED;
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(BomDetail bomDetail){
        return service.update(bomDetail)>0?DATA_UPDATE_SUCCESS:DATA_UPDATE_FAILED;
    }




    @RequestMapping("/delete")
    @ResponseBody
    public String del(@RequestParam("bdId") String bdId){
        return service.delete(bdId)>0?DATA_UDELETE_SUCCESS:DATA_DELETE_FAILED;
    }




}
