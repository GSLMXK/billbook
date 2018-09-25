package com.xk.lifebook;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@SpringBootApplication
@MapperScan("com.xk.lifebook.admin.mapper")
public class LifeBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeBookApplication.class, args);
    }

    @Bean
    public Converter<String, Date> addDateConvert() {
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
    @Bean
    public Converter<String, Integer> addIntConvert() {
        return new Converter<String, Integer>() {
            @Override
            public Integer convert(String source) {
                return Integer.valueOf(source);
            }
        };
    }
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "mysql");
        p.setProperty("supportMethodsArguments", "false");
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
