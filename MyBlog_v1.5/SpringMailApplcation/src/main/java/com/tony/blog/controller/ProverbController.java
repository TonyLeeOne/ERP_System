package com.tony.blog.controller;

import com.tony.blog.entity.Proverb;
import com.tony.blog.service.CaffeineService;
import com.tony.blog.service.ProverbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping("/proverb")
public class ProverbController {

    @Autowired
    private ProverbService proverbService;

    @Autowired
    private CaffeineService caffeineService;

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 hh:mm a");

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("proverbList", proverbService.getAllProverbs());
        return "proverbHome";
    }

    @RequestMapping("/add")
    public String add(String author, String content,ModelMap modelMap) {
        Proverb p = getProverb(author, content);
        proverbService.addProverb(p);
        modelMap.addAttribute("proverbList", proverbService.getAllProverbs());
        return "redirect:home";
    }

    @RequestMapping("/preUpdate")
    @ResponseBody
    public Proverb preUpdate(String id) {
        Proverb proverb=proverbService.findProverbById(id);
        if(proverb!=null){
            return proverb;
        }
        return null ;
    }

    @RequestMapping("/update")
    public String update(String id,String author, String content,ModelMap modelMap) {
        Proverb proverb=new Proverb();
        proverb.setId(id);
        proverb.setAuthor(author);
        proverb.setPtime(sdf.format(new Date()));
        proverb.setContent(content);
        proverbService.updateProverb(proverb);
        modelMap.addAttribute("proverbList", proverbService.getAllProverbs());
        return "redirect:/proverb/home";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id,ModelMap modelMap) {
        proverbService.deleteProverb(id);
        modelMap.addAttribute("proverbList", proverbService.getAllProverbs());
        return "redirect:/proverb/home";
    }


    @RequestMapping("/getRandomProverb")
    @ResponseBody
    public Proverb getProverb(HttpServletRequest request){

            List<Proverb> proverbs=caffeineService.getProverbs();
            Random random=new Random();
            int i=random.nextInt(proverbs.size());
            return proverbs.get(i);
    }

    private Proverb getProverb(String author, String content) {
        Proverb proverb = new Proverb();
        proverb.setAuthor(author);
        proverb.setContent(content);
        proverb.setPtime(sdf.format(new Date()));
        return proverb;
    }
}
