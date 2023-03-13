# Film-And-Television-Recommendation-System-back-end

#### 需要修改的配置：

##### application.yaml：

```yaml
#服务器端口号
server:
  port: 8080

#数据源
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/graduation_project_database?useUnicode=true&characterEncoding=UTF-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root 
    password: root 
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/java
      additional-exclude: WEB-INF/**
mybatis:
  mapper-locations: classpath:/mapper/*.xml # mapper映射文件路径
  type-aliases-package: com.recommend.entity # 该package下的类将具有默认别名
```

修改服务器端口号、数据库配置



##### CorsConfig.java

```java
package com.recommend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations:存放静态资源的路径
        //addResourceHandler:映射后的访问路径
        registry.addResourceHandler("/portraits/**").addResourceLocations("file:" + "/root/workspace/back-end/portraits/");
    }
}
```

修改存放静态资源的路径和访问路径



##### ImageUploadController.java

```java
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
```

修改图片上传的路径，即先前设置的存放静态资源的路径



