package com.tony.erp.controller.product;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Shipment;
import com.tony.erp.service.product.ShipmentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "/shipment/list";
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
        int result=shipmentService.addShip(shipment);
        if(Constant.STATUS_CANNOT_CHANGED==result){
            return Constant.NUMBER_BIG;
        }
        return result>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新出货记录
     * @param shipment
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upShip(Shipment shipment){
        int result=shipmentService.upShip(shipment);
        if(Constant.STATUS_CANNOT_CHANGED==result){
            return Constant.CANNOT_CHANGE;
        }
        return result>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }


    /**
     * 编辑出货记录
     * @return
     */
    @RequestMapping("/edit")
    public String upShip(@RequestParam(required = false,value = "sId") String sId, ModelMap modelMap){
        modelMap.addAttribute("ship",shipmentService.getShipByPrimaryKey(sId));
        return "/shipment/edit";
    }


    /**
     * 编辑出货记录
     * @return
     */
    @RequestMapping("/verify")
    public String verifyShip(@RequestParam String sId, ModelMap modelMap){
        modelMap.addAttribute("ship",shipmentService.getShipByPrimaryKey(sId));
        return "/shipment/verify";
    }





    /**
     * 删除出货记录
     * @param sId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delShip(String sId){
        int res=0;
        int reg=0;
        String[] sids=sId.split(",");
        if(sids.length>0){
            for (String id:sids) {
                res+=shipmentService.delShip(id);
                reg+=1;
            }
        }
        return res==reg?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
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
     * 确认接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/confirm")
    public String confirm(Shipment shipment){
        int res=shipmentService.confirm(shipment);
        if(Constant.STATUS_NEED_AUDIT==res){
            return Constant.NEED_AUDIT;
        }
        return res>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 审核，确认接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public String audit(Shipment shipment){
        int res=shipmentService.audit(shipment.getSId(),shipment.getSStatus());
        if(Constant.STATUS_NEED_AUDIT==res){
            return Constant.NEED_AUDIT;
        }
        return res>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 根据订单号查找出货记录
     * @return
     */
    @RequestMapping("/findByOrderNo")
    public String findByOrderNo(@Param("sOrderNo") String sOrderNo,ModelMap modelMap){
        Map<String,String> maps=new HashMap<>();
        maps.put("sOrderNo",sOrderNo);
        modelMap.addAttribute("ships",shipmentService.getByCriteria(maps));
        return "/order/history";
    }



}
