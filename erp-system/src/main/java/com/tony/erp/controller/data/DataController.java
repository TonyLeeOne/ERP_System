package com.tony.erp.controller.data;

import com.tony.erp.domain.data.DataEntity;
import com.tony.erp.service.caffeine.CaffeineService;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author tony
 * @date 2018/11/18 4:19 PM
 */
@Controller
public class DataController {

    @Autowired
    private CaffeineService caffeineService;

    @RequestMapping("/index")
    public String getAllShowData(ModelMap modelMap) {
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(ModelMap modelMap) {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setUserCount(caffeineService.getUsersTotal());
        dataEntity.setCustomCount(caffeineService.getCustomsTotal());
        dataEntity.setManOrderCount(caffeineService.getManOrdersTotal());
        dataEntity.setManPlanCount(caffeineService.getMPSTotal());
        dataEntity.setOrderCount(caffeineService.getOrdersTotal());
        dataEntity.setProductCount(caffeineService.getProductsTotal());
        modelMap.addAttribute("dataEntity", dataEntity);
        return "welcome";
    }

    @RequestMapping("/orderData")
    @ResponseBody
    public Map<String, Object> dataCollection() {
        Map<String, Object> map = DataEntity.map;
        Map<String, Object> order = ListUtils.splitToArray(caffeineService.orderDataCollection());
        Map<String, Object> material = ListUtils.splitToArray(caffeineService.materialDataCollection());
        Map<String, Object> product = ListUtils.splitToArray(caffeineService.productDataCollection());
        if (!ObjectUtils.isEmpty(order)) {
            map.put("order_data", order.get("data"));
            map.put("order_date", order.get("date"));
        }
        if (!ObjectUtils.isEmpty(material)) {
            map.put("materialName", material.get("data"));
            map.put("materialCount", material.get("date"));
        }
        if (!ObjectUtils.isEmpty(product)) {
            map.put("productName", product.get("data"));
            map.put("productCount", product.get("date"));
        }
        return map;
    }

    @RequestMapping("/system")
    public String system() {
        return "/health/system";
    }
}
