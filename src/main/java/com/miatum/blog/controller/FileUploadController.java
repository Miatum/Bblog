package com.miatum.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.miatum.blog.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
@Controller
@RequestMapping("/api/tool")
public class FileUploadController {
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @RequestMapping(value = "/savePicByFormData" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject savePicByFormData(@RequestParam("image") MultipartFile image) throws IOException {
        String filename = fileUploadUtil.savePicByFormData(image);
        JSONObject jsonObject = new JSONObject();
        String url = "http://localhost:9001/"+filename;
        jsonObject.put("success",1);
        jsonObject.put("message","上传成功");
        jsonObject.put("url",url);
        return jsonObject;
    }
}
