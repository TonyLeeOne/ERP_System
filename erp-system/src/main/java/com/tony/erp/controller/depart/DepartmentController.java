package com.tony.erp.controller.depart;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Department;
import com.tony.erp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/depart")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加新部门
     * @param department
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addDepart(Department department){
        if(ObjectUtils.isEmpty(department)){
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.addDepart(department)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    /**
     * 更新部门信息
     * @param department
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String upDepart(Department department){
        if(ObjectUtils.isEmpty(department)){
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.updateDepart(department)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    /**
     * 删除部门信息
     * @param did
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delDepart(String did){
        if(ObjectUtils.isEmpty(did)){
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.delDepart(did)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


    /**
     * 查询所有部门信息
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public List<Department> delDepart(){
       return departmentService.getAllDeparts();
    }


    /**
     * 获取单个部门信息
     * @param did
     * @return
     */
    @RequestMapping("/getDepart")
    @ResponseBody
    public Department getDepart(String did){
        return departmentService.getDepart(did);
    }
}
