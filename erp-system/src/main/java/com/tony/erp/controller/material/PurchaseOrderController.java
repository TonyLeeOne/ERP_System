package com.tony.erp.controller.material;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.domain.PurchaseOrder;
import com.tony.erp.service.PurchaseOrderService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.tony.erp.constant.Constant.ALREADY_PURGE;
import static com.tony.erp.constant.Constant.ALREADY_PURGE_STORAGE;

/**
 * @author jli2
 * @date 12/17/2018 7:29 PM
 **/
@Controller
@RequestMapping("/pO")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @RequestMapping("/getAll/{pageNum}")
    public String getAll(
            @PathVariable int pageNum,
//            String mphSn,
//            String mphStatus,
            ModelMap modelMap) {
//        MaterialPurchase param = new MaterialPurchase();
//        param.setMphSn(mphSn);
//        param.setMphStatus(mphStatus);
//        modelMap.addAttribute("po", param);
        modelMap.addAttribute("page", purchaseOrderService.getAll(pageNum));
        return "/purchase/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@Param("poId") String poId) {
        return purchaseOrderService.delPo(poId) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }


    @RequestMapping("/verify")
    public String verify(@Param("poId") String poId, ModelMap modelMap) {
        modelMap.addAttribute("po", purchaseOrderService.getPoByPrimaryKey(poId));
        return "/purchase/verify";
    }

    @RequestMapping("/audit")
    @ResponseBody
    public String audit(PurchaseOrder purchaseOrder) {
        purchaseOrder.setPoVerifier(CurrentUser.getCurrentUser().getUname());
        purchaseOrder.setPoDate(KeyGeneratorUtils.dateGenerator());
        return purchaseOrderService.updatePo(purchaseOrder) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/calculate")
    @ResponseBody
    public String calculate(String poId) {
        int res = purchaseOrderService.calculate(poId);
        if (ALREADY_PURGE_STORAGE == res) {
            return ALREADY_PURGE;
        }
        return res > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

}
