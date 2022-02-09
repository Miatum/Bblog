package com.miatum.blog.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miatum.blog.entity.ResponseEnum;
import com.miatum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    private ResponseEnum responseEnum;
    @RequestMapping("/login")
    @ResponseBody
    public String  mangerLogIn (@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        return userService.mangerLogIn(username, password).toString();
    }
}
