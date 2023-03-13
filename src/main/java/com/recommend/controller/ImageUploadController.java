package com.recommend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageUploadController {

    @PostMapping("/upload")
    public String uploadImage(MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()) {
            return "未上传图片";
        }
        else if(file.getSize()/1024 > 600) {
            return "图片大小超过600Kb";
        }
        else {
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.split("\\.")[1];
            if ("jpg".equals(fileExtension)) {
                String uuid = UUID.randomUUID().toString().replace("-","");
                String fileName = uuid + "." + fileExtension;
                //String classpath = request.getServletContext().getRealPath("/portraits/");
                String classpath = "/root/workspace/back-end/portraits/";
                String imagePath = classpath + fileName;
                File newFile = new File(imagePath);
                try {
                    file.transferTo(newFile);
                }catch (IOException e){
                    return "error";
                }
                return fileName;
            }
            else{
                return "只支持上传jpg格式的图片";
            }
        }
    }

    @GetMapping("/delete/{fileName}")
    public String uploadImage(@PathVariable("fileName") String fileName,HttpServletRequest request) {
        //String classpath = request.getServletContext().getRealPath("/portraits/");
        String classpath = "/root/workspace/back-end/portraits/";
        String deleteFileName = classpath + fileName;
        File deleteFile = new File(deleteFileName);
        deleteFile.delete();
        return "删除成功";
    }
}
