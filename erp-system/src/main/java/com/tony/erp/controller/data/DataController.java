package com.tony.erp.controller.data;

import com.tony.erp.domain.data.DataEntity;
import com.tony.erp.service.caffeine.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tony
 * @date 2018/11/18 4:19 PM
 */
@Controller
public class DataController {

    @Autowired
    private CaffeineService caffeineService;

    @RequestMapping("/index")
    public String getAllShowData(ModelMap modelMap){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(ModelMap modelMap){
        DataEntity dataEntity=new DataEntity();
        dataEntity.setUserCount(caffeineService.getUsersTotal());
        dataEntity.setCustomCount(caffeineService.getCustomsTotal());
        dataEntity.setManOrderCount(caffeineService.getManOrdersTotal());
        dataEntity.setManPlanCount(caffeineService.getMPSTotal());
        dataEntity.setOrderCount(caffeineService.getOrdersTotal());
        dataEntity.setProductCount(caffeineService.getProductsTotal());
        modelMap.addAttribute("dataEntity",dataEntity);
        return "welcome";
    }
}
