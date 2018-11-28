package com.tony.erp.service;

import com.tony.erp.domain.image.Result;
import com.tony.erp.domain.image.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author jli2
 * @date 11/16/2018 10:57 AM
 **/
@Service
public class ImageService {

    @Value("${web.upload-path}")
    private String imgPath;

    /**
     * 上传文件到本地磁盘
     * @param images
     * @return
     */
    public Result upload(List<MultipartFile> images){
        if(!CollectionUtils.isEmpty(images)){
            String[] paths=new String[images.size()];
            for (int i = 0; i <images.size() ; i++) {
                if(!images.get(i).isEmpty()){
                    String fileName = images.get(i).getOriginalFilename();
                    String filePostFixName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                    String realName = UUID.randomUUID() + filePostFixName;
                    File f = new File(imgPath, realName);
                    try {
                        images.get(i).transferTo(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    paths[i] = "/image/" + f.getName();
                }
            }
            System.out.println(ResultUtil.success(paths));
            return ResultUtil.success(paths);
        }
        return ResultUtil.fail();
    }

    /**
     * 删除上传的图片
     * @param fileName
     */
    public boolean delete(String fileName){
        fileName=fileName.substring(fileName.lastIndexOf("/")+1);
        String fullPath=imgPath+fileName;
        File file=new File(fullPath);
        if(file.exists()){
            file.delete();
            return true;
        }
        return false;
    }
}
