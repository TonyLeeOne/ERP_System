package com.tony.erp.controller.order;

import com.google.gson.Gson;
import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Order;
import com.tony.erp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getAllOrders")
    public String getAllOrders(ModelMap modelMap){
        modelMap.addAttribute("orders",orderService.getAllOrders(1));
        return "order/list";
    }

    @RequestMapping("/getAllOrders/{pageNum}")
    public String getByPage(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("orders",orderService.getAllOrders(pageNum));
        return "";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addOrders(@RequestBody Order order, HttpServletRequest httpServletRequest){
        System.out.println(order.toString());
        String data = orderService.addOrder(order)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
        return new Gson().toJson(data);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upOrder(Order order){
        return orderService.upOrder(order)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String upOrder(String oid){
        return orderService.delOrder(oid)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


}
