package com.tony.blog.controller;

import com.tony.blog.entity.Profile;
import com.tony.blog.entity.User;
import com.tony.blog.service.ProfileService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String imageHeadsUrl="/WEB-INF/static/defImages/";

    @Autowired
    private ProfileService service;

    @RequestMapping("/view")
    public String viewProfile(ModelMap modelMap,HttpServletRequest request){
        modelMap.addAttribute("profile",service.get(this.getUser().getUid()));
        modelMap.addAttribute("images",this.getHeadImgUrls(request));
        return "profile";
    }

    @RequestMapping("/update")
    public String updateProfile(String img,String signature,String email,String tel,String tag,String qq,String wechat,String github,String blog ){
        Profile profile=new Profile();
        if(img==null||img==""){
            img="/images/default.PNG";
        }
        profile.setImg(img);
        profile.setUid(this.getUser().getUid());
        profile.setBlog(blog);
        profile.setEmail(email);
        profile.setGithub(github);
        profile.setQq(qq);
        profile.setTag(tag);
        profile.setSignature(signature);
        profile.setWechat(wechat);
        profile.setTel(tel);
        service.update(profile);
        return "redirect:/profile/view";
    }

    @RequestMapping("/add")
    public String addProfile(String img,String signature,String email,String tel,String tag,String qq,String wechat,String github,String blog ){
        Profile profile=new Profile();
        if(img==null||img==""){
            img="/images/default.PNG";
        }
        profile.setImg(img);
        profile.setUid(this.getUser().getUid());
        profile.setBlog(blog);
        profile.setEmail(email);
        profile.setGithub(github);
        profile.setQq(qq);
        profile.setTag(tag);
        profile.setSignature(signature);
        profile.setWechat(wechat);
        profile.setTel(tel);
        service.insertProfile(profile);
        return "redirect:/profile/view";
    }

    @PostMapping("/get")
    @ResponseBody
    public Profile getProfile(String uid){
        System.out.println(uid);
        return service.get(uid);
    }


    private User getUser(){
        Subject subject1 = org.apache.shiro.SecurityUtils.getSubject();
        return (User) subject1.getPrincipal();
    }

    private List<String> getHeadImgUrls(HttpServletRequest request){
        List<String> imgUrls=null;
        String realPath = request.getSession().getServletContext().getRealPath(imageHeadsUrl);
        File f=new File(realPath);
        if(f.exists()&&f.isDirectory()){
            imgUrls=new ArrayList<>();
            for (File file:f.listFiles()
                    ) {
                if(file.isFile()){
                    imgUrls.add("/defImages/"+file.getName());
                }
            }
        }

        return imgUrls;
    }


}
