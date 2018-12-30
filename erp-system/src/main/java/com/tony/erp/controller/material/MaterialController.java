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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    /**
     * 首页
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("materials", materialService.getAllMaterials(1, null));
        return "/material/list";
    }

    /**
     * 分页
     * @param pageNum
     * @param mSn
     * @param mName
     * @param mStatus
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAll/{pageNum}")
    public String getAll(@PathVariable int pageNum,
                         String mSn,
                         String mName,
                         String mStatus,
                         ModelMap modelMap) {
        Material param = new Material();
        param.setmSn(mSn);
        param.setmName(mName);
        param.setmStatus(mStatus);
        modelMap.addAttribute("material", param);
        modelMap.addAttribute("page", materialService.getAllMaterials(pageNum, param));
        return "/material/list";
    }

    /**
     * 添加新物料
     *
     * @param material
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addMaterial(Material material) {
        return materialService.addMaterial(material) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新物料信息
     *
     * @param material
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upMaterial(Material material) {
        return materialService.upMaterial(material) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    @GetMapping("/edit")
    public String editMaterial(@RequestParam(defaultValue = "mSn", required = false) String mSn, ModelMap modelMap) {
        if (!StringUtils.isEmpty(mSn)) {
            modelMap.addAttribute("material", materialService.checkSnExist(mSn));
        }
        return "/material/edit";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delMaterial(String mId) {
        String[] mids = mId.split(",");
        for (String mid : mids
                ) {
            if (materialService.delMaterial(mid) < 1) {
                return Constant.DATA_DELETE_FAILED;
            }
        }
        return Constant.DATA_UDELETE_SUCCESS;
    }

    /**
     * 获取所有状态为1的物料编号信息
     *
     * @return
     */
    @GetMapping("/getAvailableMaterials")
    @ResponseBody
    public List<String> getAvailableMaterials() {
        return materialService.getAvailableMaterials();
    }

}
