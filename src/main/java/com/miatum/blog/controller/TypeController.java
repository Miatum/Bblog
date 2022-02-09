package com.miatum.blog.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miatum.blog.entity.Type;
import com.miatum.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
@Controller
@RequestMapping("/api/type/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/selctAllType")
    @ResponseBody
    public HashSet<Type> selectAllType () {
        return typeService.selectAllType();
    }
    @RequestMapping("/deleteType")
    @ResponseBody
    public int deleteType (@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        int id = jsonObject.getIntValue("id");
        return typeService.deleteType(id);
    }
    @RequestMapping("/deleteTypes")
    @ResponseBody
    public int deleteTypes (@RequestBody List<Type> types) {
        int[] ids = new int[types.size()];
        for (int i = 0; i < types.size(); i++) {
            ids[i] = types.get(i).getId();
        }
        return typeService.deleteTypes(ids);
    }
    @RequestMapping("/updateType")
    @ResponseBody
    public int updateType (@RequestBody Type type) {
        return typeService.updateType(type);
    }
    @RequestMapping("/insertType")
    @ResponseBody
    public int insertType (@RequestBody Type type) {
        typeService.insertType(type);
        int id = typeService.lastInsertId();
        return id;
    }
}
