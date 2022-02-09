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
    public ResponseEnum mangerLogIn(String username, String password) {
        User manager = userMapper.selectUserByName(username);
        Date date = new Date();
        SimpleDateFormat smpd = new SimpleDateFormat("yyyyMMdd");
        manager.setPassword(smpd.format(date) + manager.getPassword());
        try {
            if (password.equals(manager.getPassword())) {
                return responseEnum.SUCCESS;
            } else {
                return responseEnum.FAILED;
            }
        } catch (Exception e) {
            return responseEnum.EXCEPTION;
        }
    }
}
