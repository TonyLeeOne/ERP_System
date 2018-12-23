package com.tony.erp.controller.bom;

import com.tony.erp.domain.BOM;
import com.tony.erp.service.BomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.tony.erp.constant.Constant.*;

/**
 * @author jli2
 * @date 12/12/2018 11:40 AM
 **/
@Controller
@RequestMapping("/bom")
public class BomController {

    @Autowired
    private BomService bomService;

    @RequestMapping("/getAll")
    public String getAllBoms(ModelMap modelMap){
        modelMap.addAttribute("boms",bomService.findAll());
        return "/bom/list";
    }

    @RequestMapping("/edit")
    public String get(@RequestParam(value = "bId",required = false)String bId, ModelMap modelMap){
        if(!StringUtils.isEmpty(bId)){
            modelMap.addAttribute("bom",bomService.getByPrimaryKey(bId));
        }
        return "/bom/edit";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(BOM bom){
        if(bomService.checkBCodeExist(bom.getBCode())){
            return BOM_CODE_EXISTS;
        }
        return bomService.addBom(bom)>0?DATA_ADD_SUCCESS:DATA_ADD_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String del(String bId){
        return bomService.delete(bId)>0?DATA_UDELETE_SUCCESS:DATA_DELETE_FAILED;
    }

}
