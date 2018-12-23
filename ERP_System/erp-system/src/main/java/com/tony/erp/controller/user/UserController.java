package com.tony.erp.controller.user;

import com.google.gson.Gson;
import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Profile;
import com.tony.erp.domain.User;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.*;
import com.tony.erp.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tony.erp.constant.Constant.*;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

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
    public String login(@RequestBody User user, HttpSession session) {
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        cache = cacheManager.getCache("passwordRetryCache");
        if (!subject.isAuthenticated() || subject.getPrincipal() == null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpass());
            try {
                subject.login(token);
                User u = (User) subject.getPrincipal();
                if (Constant.STRING_TWO.equals(u.getStatus())) {
                    subject.logout();
                    return new Gson().toJson(ACOUNT_LOCAKED_ADMIN);
                }
                //设置session超时时间10 mins
                subject.getSession().setTimeout(600000);
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
        return "redirect:/login";
    }


    /**
     * 添加新用户
     *
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addUser(@RequestBody Map<String, String> map) {
        if (StringUtils.isEmpty(map.get("id"))) {
            String rids = map.get("rids");
            if(StringUtils.isEmpty(rids)){
                return NEED_SELECTED_ROLES;
            }
            User user = new User();
            user.setUname(map.get("uname"));
            if (userService.checkExists(user.getUname())) {
                return UNAME_EXISTS;
            }
            user.setDepartId(map.get("departId"));
            user.setUpass(SecurityUtils.getSecurityResult(user.getUname(), map.get("upass")));
            user = userService.saveUser(user);
            String[] ridsArr = rids.split(",");
            for (int i = 0; i < ridsArr.length; i++) {
                if (!userRoleService.insertUserRole(user.getId(), ridsArr[i])) {
                    return Constant.DATA_ADD_FAILED;
                }
            }
            return Constant.DATA_ADD_SUCCESS;
        } else {
            User user = userService.getByPrimaryKey(map.get("id"));
            user.setDepartId(map.get("departId"));
            user.setStatus(map.get("status"));
            if (userService.updateUser(user) < 1) {
                return Constant.DATA_UPDATE_FAILED;
            }
            userRoleService.deleteByUid(user.getId());
            String rids = map.get("rids");
            String[] ridsArr = rids.split(",");
            for (int i = 0; i < ridsArr.length; i++) {
                if (!userRoleService.insertUserRole(user.getId(), ridsArr[i])) {
                    return Constant.DATA_UPDATE_FAILED;
                }
            }
            return DATA_UPDATE_SUCCESS;
        }
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("/getAllUsers")
    public String getAllUsers(ModelMap modelMap) {
        PageHelperEntity pageHelperEntity = userService.getAllUsers(1);
        modelMap.addAttribute("users", pageHelperEntity);
        return "/user/list";
    }

    /**
     * 新增/编辑用户信息页面
     *
     * @param userId
     * @param modelMap
     * @return
     */
    @GetMapping("/edit")
    public String editUser(@RequestParam(defaultValue = "", required = false) String userId, ModelMap modelMap) {
        if (userId != null && !"".equals(userId)) {
            User user = userService.getByPrimaryKey(userId);
            modelMap.addAttribute("u", user);
        }
        modelMap.addAttribute("roles", roleService.getAllRoles());
        modelMap.addAttribute("departments", departmentService.getAllDeparts());
        return "/user/edit";
    }

    /**
     * 分页获取用户信息
     *
     * @return
     */
    @GetMapping("/getAllUsers/{pageNum}")
    public String getAllUser(@PathVariable int pageNum, ModelMap modelMap) {
        modelMap.addAttribute("page", userService.getAllUsers(pageNum));
        return "/user/list";
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
     *
     * @param map
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public String resetPassword(@RequestBody Map<String, String> map) {
        User user = userService.getByPrimaryKey(map.get("id"));
        if (!user.getUpass().equals(SecurityUtils.getSecurityResult(user.getUname(), map.get("upassOld")))) {
            return Constant.PASSWORD_INCORRECT;
        }
        user.setUpass(SecurityUtils.getSecurityResult(user.getUname(), map.get("upass")));
        return userService.updateUser(user) > 0 ? DATA_UPDATE_SUCCESS : DATA_UPDATE_FAILED;
    }

    /**
     * 密码重置
     * @param userId
     * @param modelMap
     * @return
     */
    @GetMapping("/passwordReset")
    public String reset(@RequestParam(defaultValue = "", required = false) String userId, ModelMap modelMap) {
        if (userId != null && !"".equals(userId)) {
            User user = userService.getByPrimaryKey(userId);
            modelMap.addAttribute("u", user);
        }
        return "/user/reset";
    }

    /**
     * 用户资料页面
     * @param uname
     * @param modelMap
     * @return
     */
    @GetMapping("/profile")
    public String profile(@RequestParam(defaultValue = "", required = false) String uname, ModelMap modelMap) {
        if (!StringUtils.isEmpty(uname)) {
            modelMap.addAttribute("profile", profileService.getProfileByPname(uname));
        }
        return "/profile/edit";
    }


    @PostMapping("/getProfile")
    @ResponseBody
    public Profile getProfile(String uname){
       return profileService.getProfileByPname(uname);
    }




    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(@RequestBody String[] ids) {
        if (ids.length < 1) {
            return Constant.ARG_EXCEPTION;
        }
        int res = userService.batchDeleteByIds(ids);
        return res > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }

    /**
     * 获取所有用户名
     * @return
     */
    @GetMapping("/getAllUnames")
    @ResponseBody
    public List<String> getAllUnames(){
        return userService.selectAllUnames();
    }


    /**
     * 新增/编辑用户信息页面
     *
     * @param userId
     * @return
     */
    @GetMapping("/pReset")
    @ResponseBody
    public String pReset(@RequestParam(defaultValue = "", required = false) String userId) {
        if (StringUtils.isEmpty(userId)) {
            return PASS_RESET_FAILED;
        }
        User user = userService.getByPrimaryKey(userId);
        user.setUpass(SecurityUtils.getSecurityResult(user.getUname(),TRANTIONAL_PASSWORD));
        return userService.updateUser(user)>0?PASS_RESET_SUCCESS+TRANTIONAL_PASSWORD:PASS_RESET_FAILED;
    }



}

