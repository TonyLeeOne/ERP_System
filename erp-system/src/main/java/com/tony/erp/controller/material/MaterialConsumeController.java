package com.tony.erp.controller.material;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.MaterialConsume;
import com.tony.erp.service.material.MaterialConsumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jli2
 * @date  2018/11/12
 */
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
        modelMap.addAttribute("consumes",materialConsumService.getAll(1));
        return "/mc/list";
    }

    /**
     * 分页查询领料记录
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("consumes",materialConsumService.getAll(pageNum));
        return "/mc/list";
    }

    /**
     * 新增领料记录
     * @param consume
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addConsume(MaterialConsume consume){
        System.out.println(consume.toString());
        int i=materialConsumService.addMConsume(consume);
        if(Constant.STATUS_CANNOT_CHANGED==i){
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
        System.out.println(consume);
        int i=materialConsumService.upMConsum(consume);
        if(Constant.STATUS_CANNOT_CHANGED==i){
            return Constant.NUMBER_BIG;
        }
        return i>1? Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除领料记录
     * @param mcId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delConsume(String mcId){
        String[] mcIds=mcId.split(",");
        if(mcIds.length>1){
            for (String mcid:mcIds
                 ) {
               int res= materialConsumService.delMConsume(mcid);
               if(res<0){
                   return Constant.DATA_DELETE_FAILED;
               }
            }
        }
        return  materialConsumService.delMConsume(mcId)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
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

    /**
     * 确认领料单接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/confirm")
    public String confirm(MaterialConsume consume){
        int res=materialConsumService.sureConsume(consume);
        if(Constant.STATUS_NEED_AUDIT==res){
            return Constant.NEED_AUDIT;
        }
        return res>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }


    /**
     * 审核领料单接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/verify")
    public String sure(MaterialConsume consume){
      return materialConsumService.verifyConsume(consume)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/edit")
    public String get(@RequestParam(value = "mcId",required = false)String mcId,ModelMap modelMap){
        if(!StringUtils.isEmpty(mcId)){
            modelMap.addAttribute("consume",materialConsumService.getConsume(mcId));
        }
        return "/mc/edit";
    }

    @RequestMapping("/show")
    public String show(@RequestParam(value = "mcId",required = false)String mcId,ModelMap modelMap){
        if(!StringUtils.isEmpty(mcId)){
            modelMap.addAttribute("consume",materialConsumService.getConsume(mcId));
        }
        return "/mc/show";
    }


    @RequestMapping("/sure")
    public String sure(@RequestParam(value = "mcId",required = false)String mcId,ModelMap modelMap){
        if(!StringUtils.isEmpty(mcId)){
            modelMap.addAttribute("consume",materialConsumService.getConsume(mcId));
        }
        return "/mc/confirm";
    }


}
