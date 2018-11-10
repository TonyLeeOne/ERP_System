package com.tony.erp.controller.module;

import com.tony.erp.domain.Module;
import com.tony.erp.service.caffeine.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
   private CaffeineService caffeineService;

    /**
     * @return
     */
    @GetMapping("/getAllModules")
    @ResponseBody
    public List<Module> getAllModules(){
        return caffeineService.getAllModules();
    }

}
