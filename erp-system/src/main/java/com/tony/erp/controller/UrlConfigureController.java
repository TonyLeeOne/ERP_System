package com.tony.erp.controller;

import com.tony.erp.service.UrlConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/urlConfigure")
public class UrlConfigureController {

    @Autowired
    private UrlConfigureService service;

    /**
     * 初始化所有的url,默认授权为anon
     * @return
     */
    @RequestMapping("/init")
    public String initAllUrls(ModelMap modelMap){
        modelMap.addAttribute("configures",service.insertAllUrls());
        return "";
    }

    /**
     * update urlConfigure权限
     * @return
     */
    @GetMapping("/update/{urlId}/{authority}}")
    public String upateUrlConfigure(@PathVariable String urlId,String authority){
        if(StringUtils.isEmpty(urlId)||StringUtils.isEmpty(authority)){
            return "";
        }
        service.updateUrlConfigure(urlId,authority);
        return "";
    }


}
