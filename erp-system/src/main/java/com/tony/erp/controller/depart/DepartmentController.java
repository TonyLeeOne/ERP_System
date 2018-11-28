package com.tony.erp.controller.depart;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Department;
import com.tony.erp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加新部门
     *
     * @param department
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addDepart(Department department) {
        if (ObjectUtils.isEmpty(department)) {
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.addDepart(department) > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新部门信息
     *
     * @param department
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upDepart(Department department) {
        if (ObjectUtils.isEmpty(department)) {
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.updateDepart(department) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除部门信息
     *
     * @param did
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delDepart(String did) {
        if (ObjectUtils.isEmpty(did)) {
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.delDepart(did) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }

    /**
     * 查询所有部门信息
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getAllDepartments")
    public String getAllDepartments(ModelMap modelMap) {
        List<Department> departments = departmentService.getAllDeparts();
        modelMap.addAttribute("departments", departments);
        return "/department/list";
    }

    /**
     * 新增、编辑部门
     * @param dId
     * @param modelMap
     * @return
     */
    @RequestMapping("/edit")
    public String editDepartment(@RequestParam(defaultValue = "", required = false) String dId, ModelMap modelMap) {
        if (dId != null && !"".equals(dId)) {
            Department department = departmentService.getDepart(dId);
            modelMap.addAttribute("department", department);
        }
        return "/department/edit";
    }

    /**
     * 查询所有部门信息
     * @return
     */
//    @RequestMapping("/query")
//    public String queryDepart(ModelMap modelMap){
//        modelMap.addAttribute("departments",departmentService.getAllDeparts(1));
//       return "/";
//    }


    /**
     * 查询所有部门信息
     * @return
     */
//    @RequestMapping("/query/{pageNum}")
//    public String query(@PathVariable int pageNum,ModelMap modelMap){
//        modelMap.addAttribute("departments",departmentService.getAllDeparts(pageNum));
//        return "";
//    }


    /**
     * 获取单个部门信息
     *
     * @param did
     * @return
     */
    @RequestMapping("/getDepart")
    @ResponseBody
    public Department getDepart(String did) {
        return departmentService.getDepart(did);
    }
}
