package com.miatum.blog.service;
import com.miatum.blog.entity.ResponseEnum;
import com.miatum.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miatum.blog.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    private ResponseEnum responseEnum;
    public ResponseEnum mangerLogIn(String PIN) {
        User root = userMapper.getRoot();
        Date date = new Date();
        SimpleDateFormat smpd = new SimpleDateFormat("yyyyMMdd");
        root.setPassword(smpd.format(date) + root.getPassword());
        try {
            if (PIN.equals(root.getPassword())) {
                return responseEnum.SUCCESS;
            } else {
                return responseEnum.FAILED;
            }
        } catch (Exception e) {
            return responseEnum.EXCEPTION;
        }
    }
}
