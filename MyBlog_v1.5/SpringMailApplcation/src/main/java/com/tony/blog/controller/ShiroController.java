package com.tony.blog.controller;

import com.tony.blog.entity.ShiroConfigure;
import com.tony.blog.service.CaffeineService;
import com.tony.blog.service.ShiroConfigureService;
import com.tony.blog.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class ShiroController {

    @Autowired
    private ShiroConfigureService shiroConfigureService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    CaffeineService service;


    @RequestMapping("/configureUrls")
    public String getAllUrls(ModelMap modelMap){
        modelMap.addAttribute("shiros",service.getAllPerms());
        return "management";
    }

    @RequestMapping("/getUrls")
    public String refreshAllUrls(ModelMap modelMap){
        shiroConfigureService.insertShiroConfigure();
        modelMap.addAttribute("shiros",shiroConfigureService.getAll());
        return "management";
    }


    @RequestMapping("/default")
    public String defaultAllUrls(ModelMap modelMap){
        shiroConfigureService.defaultShiroConfigure();
        modelMap.addAttribute("shiros",shiroConfigureService.getAll());
        return "management";
    }



    @RequestMapping("/update")
    @ResponseBody
    public ShiroConfigure update(String id,String authority){
        ShiroConfigure configure=shiroConfigureService.selectById(id);
        configure.setAuthority(authority);
        shiroConfigureService.updateShiro(configure);
        return shiroConfigureService.selectById(id);
    }

    @RequestMapping("/updatePerm")
    @ResponseBody
    public String updatePerm(){
        shiroService.updatePermission();
        return "权限更新成功";
    }
}
