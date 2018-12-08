package com.tony.erp.controller.product;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Storage;
import com.tony.erp.service.product.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author jli2
 * @date 11/14/2018 10:28 AM
 **/
@Controller
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 首页
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("storages", storageService.getAllStos(1));
        return "/storage/list";
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("storages", storageService.getAllStos(pageNum));
        return "/storage/list";
    }

    /**
     * 添加入库记录
     *
     * @param storage
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addStorage(Storage storage) {
        return storageService.addStorage(storage) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新入库记录
     *
     * @param storage
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upStorage(Storage storage) {
        System.out.println(storage.toString());
        return storageService.upStorage(storage) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除入库记录
     *
     * @param sId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delStorage(String sId) {
        if (sId.indexOf(Constant.SPLITTER) > 0) {
            String[] sids = sId.split(Constant.SPLITTER);
            for (String sid: sids
                 ) {
                if(storageService.delStorage(sid)<0){
                    return Constant.DATA_DELETE_FAILED;
                }
            }
        }
        return storageService.delStorage(sId)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

    /**
     * 确认入库记录
     *
     * @param storage
     * @return
     */
    @PostMapping("/confirm")
    @ResponseBody
    public String conStorage(Storage storage) {
        return storageService.confirm(storage.getStoId(), storage.getStoIndeedNum(), storage.getStoStatus()) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(required = false, defaultValue = "") String stoId, ModelMap modelMap) {
        log.info("传来的stoId为[{}]", stoId);
        if (!StringUtils.isEmpty(stoId)) {
            modelMap.addAttribute("storage", storageService.getByPrimaryKey(stoId));
        }
        return "/storage/edit";
    }


    @GetMapping("/confirm")
    public String confirm(@RequestParam(defaultValue = "")String stoId,ModelMap modelMap){
        log.info("传来的stoId为[{}]", stoId);
        if (!StringUtils.isEmpty(stoId)) {
            modelMap.addAttribute("storage", storageService.getByPrimaryKey(stoId));
        }
        return "/storage/confirm";
    }


}
