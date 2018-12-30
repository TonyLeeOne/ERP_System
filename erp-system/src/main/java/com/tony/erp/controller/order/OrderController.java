package com.tony.erp.controller.order;

import com.google.gson.Gson;
import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Order;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.OrderService;
import com.tony.erp.utils.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tony.erp.constant.Constant.ONO_EXISTS;

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
        modelMap.addAttribute("orders", orderService.getAllOrders(1, null));
        return "order/list";
    }

    @RequestMapping("/getAllOrders/{pageNum}")
    public String getByPage(@PathVariable int pageNum,
                            String oCreateDate,
                            String oNo,
                            String oStatus,
                            ModelMap modelMap) {
        Order param = new Order();
        param.setOCreateDate(oCreateDate);
        param.setONo(oNo);
        param.setOStatus(oStatus);
        modelMap.addAttribute("order", param);
        PageHelperEntity page = orderService.getAllOrders(pageNum, param);
        modelMap.addAttribute("page", page);
//        for (Object item:page.getRows()){
//            System.out.println(item);
//        }
        return "order/list";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addOrders(Order order) {
        if (orderService.checkOnoExists(order.getONo())) {
            return ONO_EXISTS;
        }
        return orderService.addOrder(order) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
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
        if (StringUtils.isEmpty(order.getOStatus())) {
            return "请选择一项出货结果";
        }
        if (StringUtils.isEmpty(order.getONote())) {
            order.setONote("暂无信息");
        }
        return orderService.confirmOrder(order.getOId(), order.getOStatus(), order.getONote()) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }


    @ResponseBody
    @RequestMapping("/getONos")
    public List<String> getONos() {
        return orderService.getONos(Constant.STRING_THREE);
    }

    @ResponseBody
    @RequestMapping("/getMPONos")
    public List<String> getMPONos() {
        return orderService.getONos(Constant.STRING_FIVE);
    }

    @ResponseBody
    @RequestMapping("/getOrderByONo")
    public Order getOrderByONo(String oNo) {
        Map<String, String> params = new HashMap(1);
        params.put("oNo", oNo);
        List<Order> orders = orderService.getByCriteria(params);
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        return orders.get(0);
    }

}
