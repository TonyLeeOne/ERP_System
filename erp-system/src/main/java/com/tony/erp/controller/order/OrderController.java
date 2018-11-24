package com.tony.erp.controller.order;

import com.google.gson.Gson;
import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Order;
import com.tony.erp.service.OrderService;
import com.tony.erp.utils.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getAllOrders")
    public String getAllOrders(ModelMap modelMap) {
        modelMap.addAttribute("orders", orderService.getAllOrders(1));
        return "order/list";
    }

    @RequestMapping("/getAllOrders/{pageNum}")
    public String getByPage(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("orders", orderService.getAllOrders(pageNum));
        return "";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addOrders(Order order) {
        String data = orderService.addOrder(order) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
        return new Gson().toJson(data);
    }

    @GetMapping("/edit")
    public String editOrder(@RequestParam(defaultValue = "", required = false) String oId, ModelMap modelMap) {
        if (oId != null && !"".equals(oId)) {
            Order order = orderService.getByOid(oId);
            modelMap.addAttribute("order", order);
        }
        return "/order/edit";
    }

    @GetMapping("/show")
    public String showOrder(@RequestParam String oId, ModelMap modelMap) {
        modelMap.addAttribute("order", orderService.getByOid(oId));
        return "/order/show";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upOrder(Order order) {
        return orderService.upOrder(order) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delOrder(String oid) {
        return orderService.delOrder(oid) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }


    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(String orderNos) {
        String[] pros = orderNos.split(",");
        return orderService.batchDelete(pros) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }


    @ResponseBody
    @RequestMapping("/confirm")
    public String confirm(Order order) {
        System.out.println(CurrentUser.getCurrentUser().getUname());
        System.out.println(order);
        return orderService.confirmOrder(order.getOId(), order.getOStatus(), order.getONote()) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }


    @ResponseBody
    @RequestMapping("/getONos")
    public List<String> getONos() {
        return orderService.getONos();
    }

    @ResponseBody
    @RequestMapping("/getOrderByONo")
    public Order getOrderByONo(String oNo) {
        System.out.println(oNo);
        Map<String, String> params = new HashMap(1);
        params.put("oNo", oNo);
        List<Order> orders = orderService.getByCriteria(params);
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        return orders.get(0);
    }

}
