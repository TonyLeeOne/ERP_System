package com.tony.blog.controller;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class MailController {

    @Autowired
    JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String sender;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(String receiver, String cc, String subject, String content, String time,RedirectAttributes redirectAttributes, HttpServletResponse response) {
       return mailSend(receiver,subject,time,content,cc,redirectAttributes);
    }

    @RequestMapping("/sendEmail")
    public String sendEmail() {
        return "sendEmail";
    }

    @RequestMapping("/sendResult")
    public String sendResult() {
        return "sendResult";
    }


    private String mailSend(String receiver,String subject,String time,String content,String cc,RedirectAttributes redirectAttributes){
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(receiver);
            messageHelper.setSubject(subject);
            messageHelper.setFrom(sender, "时光邮局");
            messageHelper.setText(content, true);
            if (!StringUtils.isEmpty(cc)) {
                messageHelper.setCc(cc);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            jms.send(message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "<p class=\"bg-danger\">发送失败，请稍后重试哦<p>");
            return "redirect:sendResult";

        }

        redirectAttributes.addFlashAttribute("msg", "<p class=\"bg-success\">发送成功<p>");

        return "redirect:sendResult";
    }
}
