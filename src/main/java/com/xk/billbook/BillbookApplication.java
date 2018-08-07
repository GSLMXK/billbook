package com.xk.billbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@MapperScan("com.xk.billbook.admin.mapper")
public class BillbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillbookApplication.class, args);
    }

    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse( source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String str = sdf.format(date);
                Date dateFmt = java.sql.Date.valueOf(str);
                return dateFmt;
            }
        };
    }
}
