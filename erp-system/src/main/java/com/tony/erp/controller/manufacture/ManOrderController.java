package com.tony.erp.controller.manufacture;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.ManOrder;
import com.tony.erp.service.ManOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jli2
 * @date 11/26/2018 2:15 PM
 **/
@Controller
@RequestMapping("/manOrder")
public class ManOrderController {

    @Autowired
    private ManOrderService manOrderService;

    @RequestMapping("/getAll")
    public String getAllManOrders(ModelMap modelMap){
        modelMap.addAttribute("manOrders",manOrderService.getAllManOrders(1));
        return "/mo/list";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "moId",required = false) String moId, ModelMap modelMap){
        if(!StringUtils.isEmpty(moId)){
            modelMap.addAttribute("mOrder",manOrderService.getManOrder(moId));
        }
        return "/mo/edit";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(ManOrder manOrder){
        return manOrderService.addManOrder(manOrder)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upManOrder(ManOrder manOrder){
        return manOrderService.upManOrder(manOrder)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String batchDelManOrders(String moId){
        int i=0;
        String[] moIds=moId.split(",");
        for (String mid:moIds
             ) {
            i=manOrderService.delMapper(mid);
            if(i<1){
                return Constant.DATA_DELETE_FAILED;
            }
        }
        return Constant.DATA_UDELETE_SUCCESS;
    }

    @RequestMapping("/getManOrdersByMpsn")
    public String getAllOrdersByPlans(@RequestParam(value = "mpsn",required = true) String mpsn,ModelMap modelMap){
        modelMap.addAttribute("manOrders",manOrderService.selectByMpSn(mpsn));
        return "/mp/history";
    }

    /**
     * 获取所有状态为2的工单号
     * @return
     */
    @RequestMapping("/getFinishedMoSn")
    @ResponseBody
    public List<String> selectFinishMoSn(){
        return manOrderService.selectFinishedMoSn();
    }


    /**
     * 获取所有状态为1的工单号
     * @return
     */
    @RequestMapping("/getAllMoSn")
    @ResponseBody
    public List<String> selectAllMoSn(){
        return manOrderService.selectAllMoSn();
    }

    /**
     * 根据工单号查找工单信息
     * @return
     */
    @RequestMapping("/getByMoSn")
    @ResponseBody
    public ManOrder selectAllMoSn(String moSn){
        return manOrderService.selectByMoSn(moSn);
    }
}
