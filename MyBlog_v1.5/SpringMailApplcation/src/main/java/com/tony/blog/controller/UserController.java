package com.tony.blog.controller;

import com.google.code.kaptcha.Constants;
import com.tony.blog.entity.User;
import com.tony.blog.service.BlogService;
import com.tony.blog.service.CaffeineService;
import com.tony.blog.service.CollectService;
import com.tony.blog.service.UserService;
import com.tony.blog.utils.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    private Cache<String, AtomicInteger> cache;

    @Autowired
    private CaffeineService caffeineService;

    @Autowired
    private CollectService collectService;

    /**
     * Ehcache Manager
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * 用户首页，可登陆、注册
     * @return
     */
    @RequestMapping("")
    public String user() {
        return "user";
    }

    /**
     * 用户注册并分配权限
     * @param username
     * @param pwd
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/register")
    public String register(String username, String pwd, RedirectAttributes redirectAttributes) {
        System.out.println(username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(SecurityUtils.getSecurityResult(username, pwd));
        user.setLevel(1);
        userService.save(user);

        Map<String, Object> paras = new HashMap<>();
        paras.put("uid", user.getUid());
        paras.put("rid", "0d44d87ba4704820b58dfbfe6f1c7bd3");
        userService.insertUserRole(paras);

        redirectAttributes.addFlashAttribute("user", user);
        redirectAttributes.addFlashAttribute("info", "注册成功，请登录");
        return "redirect:/user";
    }

    @RequestMapping("/gifCodeVerify")
    @ResponseBody
    public String getGifCode(HttpSession session) {
        return (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.POST)
    @ResponseBody
    public String getUserName(String username) {
        if ("" != username) {
            User user = userService.findUserByName(username);
            if (null == user) {
                return "sss";
            } else {
                return user.getUsername();
            }
        }
        return "";
    }


    @RequestMapping("/home")
    public String goUserHome(ModelMap modelMap) {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        modelMap.addAttribute("tags",blogService.getTags(user.getUid()));
        modelMap.addAttribute("blogs",blogService.getBlogsOfuser(user.getUid()));
        modelMap.addAttribute("collections",collectService.getByUser(user.getUid()));
        return "userHome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, String password, boolean remember, HttpSession session, RedirectAttributes redirectAttributes) {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        cache = cacheManager.getCache("passwordRetryCache");
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            token.setRememberMe(remember);
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                session.setAttribute("user", user);
            } catch (Exception e) {
                AtomicInteger retry = cache.get(name);
                if (null == retry) {
                    retry = new AtomicInteger(0);
                }
                if (retry.incrementAndGet() > 3) {
                    redirectAttributes.addFlashAttribute("info", "你的账号已被锁定，请10分钟后重试！");
                    return "redirect:/user";
                }
                cache.put(name, retry);
                redirectAttributes.addFlashAttribute("info", "账号或密码错误,还有" + (4 - retry.get()) + "次就会被锁定");
                return "redirect:/user";//返回登录页面
            }
        }

        if (cache.get(name) != null && (cache.get(name).incrementAndGet() > 4)) {
            redirectAttributes.addFlashAttribute("info", "你的账号已被锁定，请10分钟后重试！");
            return "redirect:/user";
        }else {
            cache.remove(name);
        }

        return "redirect:/index";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user";
    }


}
