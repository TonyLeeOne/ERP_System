package com.tony.erp.controller.urlconfigure;

import com.tony.erp.constant.Constant;
import com.tony.erp.service.caffeine.CaffeineService;
import com.tony.erp.service.shiro.ShiroService;
import com.tony.erp.service.shiro.UrlConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/urlConfigure")
public class UrlConfigureController {

    @Autowired
    private UrlConfigureService service;

    @Autowired
    private ShiroService shiroService;
    
    @Autowired
    private CaffeineService caffeineService;

    /**
     * 初始化所有的url,默认授权为anon
     *
     * @return
     */
    @RequestMapping("/init")
    @ResponseBody
    public String initAllUrls() {
        return service.insertAllUrls() < 0 ? Constant.DATA_UPDATE_FAILED : Constant.DATA_UPDATE_SUCCESS;
    }

    @RequestMapping("/getAllUrls")
    public String getAllUrls(ModelMap modelMap) {
        modelMap.addAttribute("urls", caffeineService.getAllUrls());
        return "permission/list";
    }

    /**
     * update urlConfigure权限
     * @return
     */
    @GetMapping("/update/{urlId}/{authority}")
    public String updateUrlConfigure(@PathVariable String urlId, @PathVariable String authority) {
        System.out.println(urlId+authority);
        if (StringUtils.isEmpty(urlId) || StringUtils.isEmpty(authority)) {
            return Constant.ARG_EXCEPTION;
        }
         service.updateUrlConfigure(urlId, authority);
        return "redirect:/urlConfigure/getAllUrls";
    }


    /**
     * 动态更新 urlConfigure权限
     * @return
     */
    @GetMapping("/updatePerm")
    @ResponseBody
    public String dynamicUpdateUrlPermission() {
        shiroService.updatePermission();
        return Constant.DATA_UPDATE_SUCCESS;
    }


}
