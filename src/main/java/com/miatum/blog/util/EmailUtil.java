package com.miatum.blog.util;

import com.miatum.blog.entity.Email;
import com.miatum.blog.entity.ResponseEnum;
import com.miatum.blog.mapper.EmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @ClassName EmailService
 * @Author Miatum
 * @date 2021.04.08 17:05
 */
@Service
public class EmailUtil {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailMapper emailMapper;
    public ResponseEnum insertEmail(Email email) {
        emailMapper.insertEmail(email);
        return ResponseEnum.SUCCESS;
    }
    public List<Email> selectAllEmail() {
        return emailMapper.selectAllEmail();
    }
    /**
     * @Description 发送文本邮件
     * @Param to-收件人邮箱，subject-标题，content-内容
     * @return
     * @Author Miatum
     * @Date 2021.04.09 09:54
     **/
    public ResponseEnum sendTextEmail(String[] to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEnum.SUCCESS;
    }
    public ResponseEnum sendHTMLEmail(String[] to, String subject, String content) {
        return ResponseEnum.SUCCESS;
    }
}
