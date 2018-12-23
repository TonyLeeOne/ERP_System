package com.tony.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jli2
 * @date  2018/11/12
 */
@SpringBootApplication
@MapperScan("com.tony.erp.dao")
public class ErpSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpSystemApplication.class, args);
    }
}
