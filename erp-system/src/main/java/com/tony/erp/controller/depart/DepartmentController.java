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

    @RequestMapping("/add")
    @ResponseBody
    public String addDepart(Department department){
        if(ObjectUtils.isEmpty(department)){
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.addDepart(department)>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upDepart(Department department){
        if(ObjectUtils.isEmpty(department)){
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.updateDepart(department)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delDepart(String did){
        if(ObjectUtils.isEmpty(did)){
            return Constant.ARG_EXCEPTION;
        }
        return departmentService.delDepart(did)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }


    @RequestMapping("/query")
    @ResponseBody
    public List<Department> delDepart(){
       return departmentService.getAllDeparts();
    }


    @RequestMapping("/getDepart")
    @ResponseBody
    public Department getDepart(String did){
        return departmentService.getDepart(did);
    }
}
