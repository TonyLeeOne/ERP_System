package com.tony.erp.controller.image;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.image.Result;
import com.tony.erp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jli2
 * @date 11/16/2018 1:05 PM
 **/
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * 上传文件
     * @param files 上传的文件
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFile(List<MultipartFile> files){
        return imageService.upload(files);
    }

    /**
     * 删除文件
     * @param fileName  文件名
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteFile(String fileName){
        return imageService.delete(fileName)==true?Constant.DATA_UDELETE_SUCCESS:Constant.DATA_DELETE_FAILED;
    }

}
