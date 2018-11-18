package com.tony.erp.controller.data;

import com.tony.erp.service.caffeine.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tony
 * @date 2018/11/18 4:19 PM
 */
@Controller
public class DataController {

    @Autowired
    private CaffeineService caffeineService;

    @RequestMapping("/index")
    public String getAllShowData(){


        return "index";
    }
}
