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
    public String addRole(String roleName,List<String> mids){
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
//        String roleName="角色管理员";
//        Role role=new Role();
//        role.setRname(roleName);
//        role.setRid("c0d8e7ef02be4aa7a1ab0a3938e9eca7");
//        List<String> mids=new ArrayList<>();
//        mids.add("495ba188daa446e380c5448a94073d41");
//        mids.add("aa83386d79f74f4fa40917b4526ca22a");
//        mids.add("a4e6f7a2c46e4ae99620b204f8ec4da8");
//        mids.add("0f63060888d44433b07ae23fd7ca3e41");
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
