package com.tony.erp.controller.product;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Shipment;
import com.tony.erp.service.product.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jli2
 * @date 11/12/2018 5:33 PM
 **/
@Controller
@RequestMapping("/ship")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    /**
     * 首页
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap){
        modelMap.addAttribute("ships",shipmentService.getAllShips(1));
        return "";
    }

    /**
     * 根据页数查找出货记录
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("ships",shipmentService.getAllShips(pageNum));
        return "";
    }

    /**
     * 添加新出货记录
     * @param shipment
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public String addShip(Shipment shipment){
        return shipmentService.addShip(shipment)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新出货记录
     * @param shipment
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public String upShip(Shipment shipment){
        return shipmentService.upShip(shipment)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }


    /**
     * 更新出货记录
     * @param sId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delShip(String sId){
        return shipmentService.delShip(sId)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


    /**
     * 根据字段查找对应出货记录
     * @param key  成品字段
     * @param value
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShipsByKey")
    public List<Shipment> getShipsByOrderNo(String key,String value){
        return shipmentService.getByKey(key,value);
    }


    /**
     * 审核，确认接口
     * @param sId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping("/confirm")
    public String confirm(String sId,String status){
        return shipmentService.audit(sId,status)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }



}
