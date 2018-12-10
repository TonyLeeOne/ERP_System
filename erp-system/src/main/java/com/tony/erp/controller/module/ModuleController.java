package com.tony.erp.controller.module;

import com.tony.erp.service.caffeine.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
   private CaffeineService caffeineService;

    @GetMapping("/getAllModules")
    public String getAllModules(ModelMap modelMap) {
        modelMap.addAttribute("modules", caffeineService.getAllModules());
        return "/module/list";
    }

}
