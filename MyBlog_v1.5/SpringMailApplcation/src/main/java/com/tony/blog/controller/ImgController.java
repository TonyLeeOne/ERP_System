package com.tony.blog.controller;

import com.google.gson.Gson;
import com.tony.blog.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ImgController {

    @Value("${newFilePath}")
    private String newFilePath;

    /**
     * 图片上传
     *
     * @param files
     * @param request
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImage(@RequestParam(value = "myFileName") List<MultipartFile> files, HttpServletRequest request, HttpSession session) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath(newFilePath);
        return this.getImagePaths(files, realPath);
    }


    /**
     * 对要上传的图片进行处理并返回结果
     * @param files
     * @param realPath
     * @return
     */
    private Object getImagePaths(List<MultipartFile> files, String realPath) {
        if (!CollectionUtils.isEmpty(files) && files.size() > 0) {
            String[] filePaths = new String[files.size()];
            System.out.println(files.size());
            for (int i=0;i<files.size();i++) {
                if (!files.get(i).isEmpty()) {
                    String fileName = files.get(i).getOriginalFilename();
                    String filePostFixName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                    String realName = UUID.randomUUID() + filePostFixName;
                    File f = new File(realPath, realName);
                    try {
                        files.get(i).transferTo(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    filePaths[i] = "/uploadImgs/" + f.getName();
                }

            }
            System.out.println(filePaths.toString());
            return new Gson().toJson(ResultUtils.success(filePaths));
        } else {
            return new Gson().toJson(ResultUtils.fail());
        }
    }


}
