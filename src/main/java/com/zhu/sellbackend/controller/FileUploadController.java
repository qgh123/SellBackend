package com.zhu.sellbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhu.sellbackend.base.BaseModel;
import com.zhu.sellbackend.oss.OssServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName FileUploadController
 * @Description
 * @Author qgh
 * @Date 2020-03-04 11:26
 **/
@RestController
@Slf4j
@RequestMapping("/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private OssServer ossServer;

    @PostMapping("/upload")
    public BaseModel<JSONObject> uploadFile(@RequestParam("fileObj") MultipartFile file) {
        BaseModel<JSONObject> model = new BaseModel<>();
        JSONObject ojb = new JSONObject();
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            //在文件名中加入随机字符串防止重名
            Long date = System.currentTimeMillis();
            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            String randomStr = uuid.substring(0,10) + date;
            String fileName = randomStr + "-" +originalFilename;
            String response = ossServer.uploadFile(inputStream, fileName);
            JSONObject jsonObject = JSONObject.parseObject(response);
            log.info("图片上传成功", jsonObject.get("key"));
            model.setData(jsonObject);
            model.setStatus("200");
            return model;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ojb.put("error", e.getMessage());
            model.setData(ojb);
            model.setStatus("500");
            return model;
        }
    }

}
