package com.tony.blog.controller;

import com.tony.blog.entity.Role;
import com.tony.blog.entity.RoleAndModule;
import com.tony.blog.entity.UserAndRole;
import com.tony.blog.service.CaffeineService;
import com.tony.blog.service.RoleAndModuleService;
import com.tony.blog.service.RoleService;
import com.tony.blog.service.UserAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CaffeineService caffeineService;

    @Autowired
    private RoleAndModuleService roleAndModuleService;

    @Autowired
    private UserAndRoleService userAndRoleService;

    @RequestMapping("/management")
    public String getAllRoles(ModelMap modelMap){

        modelMap.addAttribute("modules",caffeineService.getAllModules());
        modelMap.addAttribute("roles",roleService.getAll());
        modelMap.addAttribute("users",caffeineService.getAllUsers());

        return "userManagement";
    }

    @PostMapping("/add")
    public String addRole(ModelMap modelMap,String roleName,String add,String update,String query,String delete){
        if(roleName!=null&&(add!=null||update!=null||query!=null||delete!=null)&&!roleService.checkRoleExist(roleName)){
            Role role=new Role();
            role.setRname(roleName);
            role=roleService.insertRole(role);

            List<RoleAndModule> list=new ArrayList<>();
            getRm(list,add,role);
            getRm(list,update,role);
            getRm(list,query,role);
            getRm(list,delete,role);

            roleAndModuleService.insertRoleAndModule(list);
        }

        return "redirect:/role/management";
    }


    @GetMapping("/change/{modules}/{rid}")
    public String changeModules(@PathVariable List<String> modules,@PathVariable String rid){
        roleAndModuleService.changeModules(modules,rid);
        return "redirect:/role/management";
    }

    @GetMapping("/change//{rid}")
    public String deleteModules(@PathVariable String rid){
        RoleAndModule roleAndModule=new RoleAndModule();
        roleAndModule.setRid(rid);
       roleAndModuleService.delete(roleAndModule);
        return "redirect:/role/management";
    }


    private List<RoleAndModule> getRm(List<RoleAndModule> list,String str,Role role){
        if(!StringUtils.isEmpty(str)){
            list.add(new RoleAndModule(role.getRid(),str));
        }
        return list;
    }

    @GetMapping("/add/{rid}/{mid}")
    public String addModule(@PathVariable String rid,@PathVariable String mid){
        boolean exists=roleAndModuleService.checkExist(new RoleAndModule(rid,mid));
        if(!exists){
            List<RoleAndModule> list=new ArrayList<>();
            list.add(new RoleAndModule(rid,mid));
            roleAndModuleService.insertRoleAndModule(list);
        }
        return "redirect:/role/management";
    }

    @GetMapping("/changerole/{uid}/{rid}")
    public String changeRole(@PathVariable String uid,@PathVariable String rid){
        if(!StringUtils.isEmpty(uid)&&!StringUtils.isEmpty(rid)){
            userAndRoleService.update(new UserAndRole(uid,rid));
        }

        return "redirect:/role/management";
    }


    @GetMapping("/delete/{rid}")
    public String deleteRole(@PathVariable String rid){
        boolean exists=userAndRoleService.checkRoleAndUserExist(rid);
        if(!exists){
            RoleAndModule roleAndModule=new RoleAndModule();
            roleAndModule.setRid(rid);
            roleAndModuleService.delete(roleAndModule);
            roleService.deleteRole(rid);
        }
        return "redirect:/role/management";
    }




}
