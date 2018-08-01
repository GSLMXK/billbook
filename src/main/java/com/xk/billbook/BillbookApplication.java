package com.xk.billbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xk.billbook.admin.mapper")
public class BillbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillbookApplication.class, args);
    }
}
