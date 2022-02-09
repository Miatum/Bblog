package com.miatum.blog.controller;

import com.miatum.blog.entity.Blog;
import com.miatum.blog.entity.Email;
import com.miatum.blog.entity.ResponseEnum;
import com.miatum.blog.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @ClassName EmailContraoller
 * @Author Miatum
 * @date 2021.04.08 17:11
 */
@Controller
@RequestMapping("/api/email")
public class EmailController {
    private ResponseEnum responseEnum;
    @Autowired
    private EmailUtil emailService;
    @RequestMapping("/insertEmail")
    @ResponseBody
    public String insertEmail(@RequestBody Email email) {
        return emailService.insertEmail(email).getMessage();
    }
    @RequestMapping("/sendTextEmail")
    @ResponseBody
    public String sendTextEmail() {
        String[] to = {"zhoumq@pvirtech.com"};
        String subject = "测试";
        String content = "2021/04/08";
        responseEnum = emailService.sendTextEmail(to, subject, content);
        return responseEnum.getMessage();
    }
    @RequestMapping("/sendEmailToSubscriber")
    @ResponseBody
    public String sendEmailToSubscriber(@RequestBody Blog blog) {
        List<Email> emails = emailService.selectAllEmail();
        String[] address = new String[emails.size()];
        for (int i = 0; i < address.length; i++) {
            address[i] = emails.get(i).getAddress();
        }
        responseEnum = emailService.sendTextEmail(address, blog.getTitle(), blog.getContent());
        return responseEnum.getMessage();
    }
}
