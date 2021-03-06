package com.miatum.blog.controller;

import com.miatum.blog.entity.Blog;
import com.miatum.blog.util.MoYuBan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @ClasName TestController
 * @Author Miatum
 * @date 2021.04.12 16:20
 */
@Controller
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MoYuBan moYuBan;
    @RequestMapping("/redis")
    @ResponseBody
    public List<Blog> redisTest() {
        List<Blog> blogs = (List<Blog>) redisTemplate.opsForValue().get("allBlog");
        return blogs;
    }
    @RequestMapping("/moyuban")
    @ResponseBody
    public String moYuBan() {
        return moYuBan.getMessage();
    }
}
