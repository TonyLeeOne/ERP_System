package com.tony.erp.controller.role;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Role;
import com.tony.erp.service.RoleModuleService;
import com.tony.erp.service.RoleService;
import com.tony.erp.service.UserRoleService;
import com.tony.erp.service.caffeine.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
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
     * @return
     */
    @GetMapping("/getAllRoles")
    @ResponseBody
    public List<Role> getAllRoles(){
        return caffeineService.getAllRoles();
    }


    /**
     * ajax新增角色及权限信息
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addRole(List<String> mids,String roleName){
        Role role=roleService.addRole(roleName);
        if(ObjectUtils.isEmpty(role)||CollectionUtils.isEmpty(mids)){
            return Constant.ARG_EXCEPTION;
        }
       return roleModuleService.insert(mids,role.getRid())<0?Constant.DATA_ADD_FAILED:Constant.DATA_ADD_SUCCESS;
    }

    /**
     * 更新角色及权限信息
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public String upRole(Role role,List<String> mids){
        if(ObjectUtils.isEmpty(role)||CollectionUtils.isEmpty(mids)){
            return Constant.ARG_EXCEPTION;
        }
        return roleService.updateRole(role)+roleModuleService.insert(mids,role.getRid())<0?Constant.DATA_UPDATE_FAILED:Constant.DATA_UPDATE_SUCCESS;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteRole(String rid){
        if(userRoleService.check(rid)){
            return Constant.CHECK_INFO;
        }
        if(StringUtils.isEmpty(rid)){
            return Constant.ARG_EXCEPTION;
        }
        return userRoleService.deleteByRid(rid)+roleModuleService.deleteByRid(rid)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }






}
