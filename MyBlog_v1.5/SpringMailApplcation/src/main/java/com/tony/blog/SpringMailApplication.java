package com.tony.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tony.blog.dao")
public class SpringMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMailApplication.class,args);
    }
}
