package com.tony.erp.controller.product;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Storage;
import com.tony.erp.service.product.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jli2
 * @date 11/14/2018 10:28 AM
 **/
@Controller
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 首页
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap){
        modelMap.addAttribute("storages",storageService.getAllStos(1));
        return "";
    }

    /**
     * 分页查询
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("storages",storageService.getAllStos(pageNum));
        return "";
    }

    /**
     * 添加入库记录
     * @param storage
     * @return
     */
    @RequestMapping("/add")
    public String addStorage(Storage storage){
        return storageService.addStorage(storage)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新入库记录
     * @param storage
     * @return
     */
    @RequestMapping("/update")
    public String upStorage(Storage storage){
        return storageService.upStorage(storage)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除入库记录
     * @param sId
     * @return
     */
    @RequestMapping("/delete")
    public String delStorage(String sId){
        return storageService.delStorage(sId)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

    /**
     * 确认入库记录
     * @param sId
     * @return
     */
    @RequestMapping("/confirm")
    public String conStorage(String sId,int indeed,String status){
        return storageService.confirm(sId,indeed,status)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }


}
