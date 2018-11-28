package com.tony.erp.controller.material;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.service.material.MaterialPurchaseService;
import com.tony.erp.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    /**
     * 首页
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap){
        modelMap.addAttribute("materials",materialService.getAllMaterials(1));

        return "";
    }

    /**
     * 分页
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("materials",materialService.getAllMaterials(pageNum));
        return "";
    }

    /**
     * 添加新物料
     * @param material
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addMaterial(Material material){
        return materialService.addMaterial(material)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新物料信息
     * @param material
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upMaterial(Material material){
        return materialService.upMaterial(material)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }




}
