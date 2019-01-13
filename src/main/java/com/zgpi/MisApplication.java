package com.zgpi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.zgpi.*.dao")
@EnableCaching
public class MisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisApplication.class, args);
    }

}

