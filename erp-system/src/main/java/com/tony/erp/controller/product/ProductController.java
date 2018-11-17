package com.tony.erp.controller.product;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Product;
import com.tony.erp.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/getAll")
    public String getAllProducts(ModelMap modelMap){
        modelMap.addAttribute("products",productService.getAllProducts(1));
        return "";
    }

    @RequestMapping("/getAll/{pageNum}")
    public String getAllProducts(@PathVariable int pageNum, ModelMap modelMap){
        modelMap.addAttribute("products",productService.getAllProducts(pageNum));
        return "";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addProduct(Product product){
        int result=productService.addProduct(product);
        if(Constant.STATUS_CANNOT_CHANGED==result){
            return Constant.PROD_CODE_EXISTS;
        }
        return result>0?Constant.DATA_ADD_SUCCESS:Constant.DATA_ADD_FAILED;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String upProduct(Product product){
        return productService.upProduct(product)>0?Constant.DATA_UPDATE_SUCCESS:Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delProduct(String pid){
        return productService.delProduct(pid)>0?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

    @RequestMapping("/getProduct")
    @ResponseBody
    public Product getProduct(String pid){
        return productService.getProduct(pid);
    }
}
