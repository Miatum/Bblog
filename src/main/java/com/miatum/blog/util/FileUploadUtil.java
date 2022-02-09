package com.miatum.blog.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;
@Service
public class FileUploadUtil {
    // 图片服务器
    @Value("${com.miatum.blog.picAdrress}")
    private String path;
    @Value("${com.miatum.blog.picServer}")
    private String server;
    public String savePicByFormData (MultipartFile file) throws IOException {
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        String fileName_origin = file.getOriginalFilename();
        int lastIndexOf = fileName_origin.lastIndexOf(".");
        String suffix = fileName_origin.substring(lastIndexOf);
        String fileName = server + UUID.randomUUID().toString().replace("-","") + suffix;
        File tempFile = new File(path,fileName);
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return fileName;
    }
}
