package com.tony.erp.controller.material;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.MaterialConsume;
import com.tony.erp.service.material.MaterialConsumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/materialConsume")
public class MaterialConsumeController {

    @Autowired
    private MaterialConsumService materialConsumService;

    /**
     * 查询首页领料记录
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap){
        modelMap.addAttribute("comsumes",materialConsumService.getAll(1));
        return "";
    }

    /**
     * 分页查询领料记录
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("purchases",materialConsumService.getAll(pageNum));
        return "";
    }

    /**
     * 新增领料记录
     * @param consume
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addConsume(MaterialConsume consume){
        int i=materialConsumService.addMConsume(consume);
        if(i==-2){
            return Constant.NUMBER_BIG;
        }
        return i>1? Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }


    /**
     * 更新领料记录
     * @param consume
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upConsume(MaterialConsume consume){
        int i=materialConsumService.upMConsum(consume);
        if(i==-2){
            return Constant.NUMBER_BIG;
        }
        return i>1? Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除领料记录
     * @param mcid
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delConsume(String mcid){
        return materialConsumService.delMConsume(mcid)>1? Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

    /**
     * 根据料号查找所有领料记录
     * @param msn
     * @return
     */
    @RequestMapping("/getByMsn")
    @ResponseBody
    public List<MaterialConsume> getConsumeByMsn(String msn){
        return materialConsumService.getByMsn(msn);
    }

}
