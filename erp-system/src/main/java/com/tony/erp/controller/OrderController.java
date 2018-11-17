package com.tony.erp.controller;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Order;
import com.tony.erp.service.OrderService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getAllOrders")
    public String getAllOrders(ModelMap modelMap){
        modelMap.addAttribute("orders",orderService.getAllOrders(1));
        return "";
    }

    @RequestMapping("/getAllOrders/{pageNum}")
    public String getByPage(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("orders",orderService.getAllOrders(pageNum));
        return "";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addOrders(Order order){
        return orderService.addOrder(order)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upOrder(Order order){
        return orderService.upOrder(order)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String upOrder(String o_id){
        return orderService.delOrder(o_id)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


}
