package com.tony.erp.controller.role;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Module;
import com.tony.erp.domain.Role;
import com.tony.erp.service.RoleModuleService;
import com.tony.erp.service.RoleService;
import com.tony.erp.service.UserRoleService;
import com.tony.erp.service.caffeine.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private CaffeineService caffeineService;

    @Autowired
    private RoleModuleService roleModuleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * ajax取所有的roles
     *
     * @return
     */
    @GetMapping("/getAllRoles")
//    @ResponseBody
    public String getAllRoles(ModelMap modelMap) {
        modelMap.addAttribute("roles", caffeineService.getAllRoles());
        return "/role/list";
    }

    /**
     * ajax新增、更新角色及权限信息
     *
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addRole(@RequestBody List<String> mids,
                          @RequestParam String roleName,
                          @RequestParam(defaultValue = "", required = false) String rid) {
        if (rid != null && !"".equals(rid)) {//更新
            Role role = roleService.selectByPrimaryKey(rid);
            if (role.getRname() != roleName) {
                role.setRname(roleName);
                Integer result = roleService.updateRole(role);
            }
        } else {//新增
            Role role = roleService.addRole(roleName);
            if (ObjectUtils.isEmpty(role) || CollectionUtils.isEmpty(mids)) {
                return Constant.ARG_EXCEPTION;
            }
            rid = role.getRid();
        }
        return roleModuleService.insert(mids, rid) < 0 ? Constant.DATA_ADD_FAILED : Constant.DATA_ADD_SUCCESS;
    }

    /**
     * 新增、编辑页面
     *
     * @param rid
     * @param modelMap
     * @return
     */
    @RequestMapping("/edit")
    public String editRole(@RequestParam(defaultValue = "", required = false) String rid, ModelMap modelMap) {
        if (rid != null && !"".equals(rid)) {
            Role role = roleService.selectByPrimaryKey(rid);
            modelMap.addAttribute("role", role);
            List<String> roleModules = roleModuleService.selectMidByRid(rid);
            modelMap.addAttribute("roleModules", roleModules);

        }
        List<Module> modules = caffeineService.getAllModules();
        modelMap.addAttribute("modules", modules);
        return "/role/edit";
    }

    /**
     * 根据主键删除
     *
     * @param rid
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public String deleteRole(String rid) {
        if (userRoleService.check(rid)) {
            return Constant.CHECK_INFO;
        }
        if (StringUtils.isEmpty(rid)) {
            return Constant.ARG_EXCEPTION;
        }
        return userRoleService.deleteByRid(rid) + roleModuleService.deleteByRid(rid) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
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
        for (String id : ids) {
            if (userRoleService.check(id)) {
                return "有角色因被用户使用，不可删除";
            }
        }
        int res = roleService.batchDeleteByIds(ids);
        return res > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }
}
