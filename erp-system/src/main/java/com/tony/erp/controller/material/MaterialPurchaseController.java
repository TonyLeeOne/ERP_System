package com.tony.erp.controller.material;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.service.material.MaterialPurchaseService;
import com.tony.erp.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/materialPurchase")
public class MaterialPurchaseController {

    @Autowired
    private MaterialPurchaseService materialPurchaseService;

    /**
     * 查询首页采购记录
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap){
        modelMap.addAttribute("purchases",materialPurchaseService.getAll(1));
        return "/purchase/list";
    }

    /**
     * 分页查询从采购记录
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("purchases",materialPurchaseService.getAll(pageNum));
        return "";
    }

    /**
     * 新增采购记录
     * @param materialPurchase
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addPurchase(MaterialPurchase materialPurchase){
        return materialPurchaseService.addMPurchase(materialPurchase)>0? Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }


    /**
     * 根据主键更新采购记录
     * @param materialPurchase
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upPurchase(MaterialPurchase materialPurchase){
        return materialPurchaseService.upMPurchase(materialPurchase)>1? Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除采购记录
     * @param mpid
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delPurchase(String mpid){
        if(mpid.indexOf(Constant.SPLITTER)>0){
            String[] mpids=mpid.split(Constant.SPLITTER);
            for (String mphId:mpids
                 ) {
                if(materialPurchaseService.delMPurchase(mphId)<1){
                    return Constant.DATA_DELETE_FAILED;
                }
            }
            return Constant.DATA_UDELETE_SUCCESS;
        }
        return materialPurchaseService.delMPurchase(mpid)>0? Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


    /**
     * 根据料号msn查找所有采购记录
     * @param msn
     * @return
     */
    @RequestMapping("/getByMsn")
    @ResponseBody
    public List<MaterialPurchase> getByMsn(String msn){
        return materialPurchaseService.getByMphSn(msn);
    }



    @GetMapping("/edit")
    public String editMaterial(@RequestParam(defaultValue = "", required = false) String mphId, ModelMap modelMap) {
        if (!StringUtils.isEmpty(mphId)) {
            modelMap.addAttribute("purchase",materialPurchaseService.getMaterialPurchase(mphId));
        }
        return "/purchase/edit";
    }


    @GetMapping("/verify")
    public String verifyMaterial(@RequestParam(defaultValue = "", required = false) String mphId, ModelMap modelMap) {
        if (!StringUtils.isEmpty(mphId)) {
            modelMap.addAttribute("purchase",materialPurchaseService.getMaterialPurchase(mphId));
        }
        return "/purchase/verify";
    }


    /**
     * 审核
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public String audit(MaterialPurchase purchase){
        return materialPurchaseService.audit(purchase)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }



    /**
     * 确认接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/confirm")
    public String confirm(MaterialPurchase purchase){
        return materialPurchaseService.confirm(purchase)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }




}
