package com.tony.erp.controller.product;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Order;
import com.tony.erp.domain.Product;
import com.tony.erp.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/getAll")
    public String getAllProducts(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.getAllProducts(1,null));
        return "/product/list";
    }

    @RequestMapping("/getAll/{pageNum}")
    public String getAllProducts(@PathVariable int pageNum,
                                 String proStatus,
                                 String proCode,
                                 String proName,
                                 ModelMap modelMap) {
        Product param = new Product();
        param.setProCode(proCode);
        param.setProName(proName);
        param.setProStatus(proStatus);
        modelMap.addAttribute("product", param);

        modelMap.addAttribute("page", productService.getAllProducts(pageNum, param));
        return "/product/list";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addProduct(Product product) {
        int result = productService.addProduct(product);
        if (Constant.STATUS_CANNOT_CHANGED == result) {
            return Constant.PROD_CODE_EXISTS;
        }

        return result > 0 ? Constant.DATA_ADD_SUCCESS : Constant.DATA_ADD_FAILED;
    }

    @GetMapping("/edit")
    public String editOrder(@RequestParam(defaultValue = "proCode", required = false) String proCode, ModelMap modelMap) {
        if (!StringUtils.isEmpty(proCode)) {
            modelMap.addAttribute("product", productService.getProduct(proCode));
        }
        return "/product/edit";
    }


    @RequestMapping("/update")
    @ResponseBody
    public String upProduct(Product product) {
        return productService.upProduct(product) > 0 ? Constant.DATA_UPDATE_SUCCESS : Constant.DATA_UPDATE_FAILED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delProduct(String pid) {
        return productService.delProduct(pid) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }

    @RequestMapping("/getProduct")
    @ResponseBody
    public Product getProduct(String pid) {
        return productService.getProduct(pid);
    }


    @PostMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(String proCodes) {
        String[] pros = proCodes.split(",");
        return productService.batchDelete(pros) > 0 ? Constant.DATA_UDELETE_SUCCESS : Constant.DATA_DELETE_FAILED;
    }

    @GetMapping("/populateProCodes")
    @ResponseBody
    public List<String> batchDelete() {
        return productService.selectProCodes();
    }
}
