package com.tony.erp.controller;

import com.tony.erp.domain.User;
import com.tony.erp.service.UserService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Ehcache Manager
     */
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> cache;


    /**
     * 跳转到login页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 用户登录
     * @param user
     * @param remember
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public String login(User user, boolean remember, HttpSession session, RedirectAttributes redirectAttributes){
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        cache = cacheManager.getCache("passwordRetryCache");
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpass());
            token.setRememberMe(remember);
            try {
                subject.login(token);
                User u = (User) subject.getPrincipal();
                session.setAttribute("user", user);
            } catch (Exception e) {
                AtomicInteger retry = cache.get(user.getUname());
                if (null == retry) {
                    retry = new AtomicInteger(0);
                }
                if (retry.incrementAndGet() > 3) {
                    redirectAttributes.addFlashAttribute("info", "你的账号已被锁定，请10分钟后重试！");
                    return "redirect:/login";
                }
                cache.put(user.getUname(), retry);
                redirectAttributes.addFlashAttribute("info", "账号或密码错误,还有" + (4 - retry.get()) + "次就会被锁定");
                return "redirect:/login";//返回登录页面
            }
        }

        if (cache.get(user.getUname()) != null && (cache.get(user.getUname()).incrementAndGet() > 4)) {
            redirectAttributes.addFlashAttribute("info", "你的账号已被锁定，请10分钟后重试！");
            return "redirect:/login";
        }else {
            cache.remove(user.getUname());
        }

        return "redirect:/index";
    }


    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public String saveUser(User user){
        if(!ObjectUtils.isEmpty(user)){
            userService.saveUser(user);
            return "新用户添加成功";
        }
        return "参数不合法,请重新输入";
    }

}
