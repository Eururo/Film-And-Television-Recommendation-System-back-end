package com.recommend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@MapperScan("com.recommend.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}


