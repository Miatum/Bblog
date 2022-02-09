package com.miatum.blog.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miatum.blog.entity.Tag;
import com.miatum.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
@Controller
@RequestMapping ("/api/tag/")
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping("/selctAllTag")
    @ResponseBody
    public HashSet<Tag> selectAllTag () {
        return tagService.selectAllTag();
    }
    @RequestMapping("/deleteTag")
    @ResponseBody
    public int deleteTag (@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        int id = jsonObject.getIntValue("id");
        return tagService.deleteTag(id);
    }
    @RequestMapping("/deleteTags")
    @ResponseBody
    public int deleteTags (@RequestBody List<Tag> tags) {
        int[] ids = new int[tags.size()];
        for (int i = 0;i < tags.size();i++){
            ids[i] = tags.get(i).getId();
        }
        return tagService.deleteTags(ids);
    }
    @RequestMapping("/updateTag")
    @ResponseBody
    public int updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }
    @RequestMapping("/insertTag")
    @ResponseBody
    public int insertTag(@RequestBody Tag tag){
        tagService.insertTag(tag);
        int id = tagService.lastInsertId();
        return id;
    }
}
