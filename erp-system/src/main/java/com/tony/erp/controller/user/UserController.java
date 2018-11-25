package com.tony.erp.controller.user;

import com.google.gson.Gson;
import com.tony.erp.domain.Profile;
import com.tony.erp.domain.User;
import com.tony.erp.service.ProfileService;
import com.tony.erp.service.UserRoleService;
import com.tony.erp.service.UserService;
import com.tony.erp.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tony.erp.constant.Constant.*;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;
    /**
     * Ehcache Manager
     */
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> cache;

    @Autowired
    private ProfileService profileService;

    /**
     * 跳转到login页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Ajax用户登录
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public String login(@RequestBody User user,  HttpSession session) {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        cache = cacheManager.getCache("passwordRetryCache");
        if (!subject.isAuthenticated()||subject.getPrincipal()==null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpass());
            try {
                subject.login(token);
//                设置session超时时间30 mins
//                subject.getSession().setTimeout(1800000);
                User u = (User) subject.getPrincipal();
                session.setAttribute("user", u);
            } catch (Exception e) {
                AtomicInteger retry = cache.get(user.getUname());
                if (null == retry) {
                    retry = new AtomicInteger(0);
                }
                if (retry.incrementAndGet() > 3) {
                    return new Gson().toJson(ACOUNT_LOCAKED);
                }
                cache.put(user.getUname(), retry);
                return new Gson().toJson("账号或密码错误,还有" + (4 - retry.get()) + "次就会被锁定");
            }
        }
        if (cache.get(user.getUname()) != null && (cache.get(user.getUname()).incrementAndGet() > 4)) {
            return new Gson().toJson(ACOUNT_LOCAKED);
        } else {
            cache.remove(user.getUname());
        }
        return new Gson().toJson(LOGIN_SUCCESS);
    }


    /**
     * 注销登录
     *
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
     *
     * @param user 包含uname,upass,did
     * @param rid
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addUser(User user, String rid) {
        user.setUpass(SecurityUtils.getSecurityResult(user.getUname(), user.getUpass()));
        if (ObjectUtils.isEmpty(user)) {
            return ARG_EXCEPTION;
        }
        User user1 = userService.saveUser(user);
        boolean result = userRoleService.insertUserRole(user1.getId(), rid);
        if (result) {
            return DATA_ADD_SUCCESS;
        }
        return DATA_ADD_FAILED;
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("/getAllUsers")
    public String getAllUsers(int pageSize, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers(1));
        return "";
    }

    /**
     * 分页获取用户信息
     *
     * @return
     */
    @GetMapping("/getAllUsers/{pageNum}")
    public String getAllUser(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers(pageNum));
        return "";
    }


    /**
     * 添加用户资料
     *
     * @return
     */
    @PostMapping("/addProfile")
    @ResponseBody
    public String addUserProfile(Profile profile) {
        return profileService.addProfile(profile) > 0 ? DATA_ADD_SUCCESS : DATA_ADD_FAILED;
    }

    /**
     * 修改用户资料
     *
     * @param profile
     * @return
     */
    @PostMapping("/upProfile")
    @ResponseBody
    public String upUserProfile(Profile profile) {
        return profileService.upProfile(profile) > 0 ? DATA_UPDATE_SUCCESS : DATA_UPDATE_FAILED;
    }


    /**
     * 重置密码
     * @param user
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public String resetPassword(User user) {
        user.setUpass(SecurityUtils.getSecurityResult(user.getUname(), user.getUpass()));
        return userService.updateUser(user) > 0 ? DATA_UPDATE_SUCCESS : DATA_UPDATE_FAILED;
    }

    /**
     * 添加新用户时判断用户名是否重复
     *
     * @param uname
     * @return
     */
    @RequestMapping("/asyncUname")
    @ResponseBody
    public String asyncGetUname(String uname) {
        if (StringUtils.isEmpty(uname)) {
            return ARG_EXCEPTION;
        }
        if (userService.checkExists(uname)) {
            return UNAME_EXISTS;
        }
        return UNAME_NOT_EXISTS;

    }


}
