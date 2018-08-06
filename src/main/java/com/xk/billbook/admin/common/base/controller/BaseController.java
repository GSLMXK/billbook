package com.xk.billbook.admin.common.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xk.billbook.admin.common.base.utils.TestPage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@Controller
public class BaseController<E> {

    private final String ERROR_URL = "admin/shopCart/error";

    //返回错误页
    public String toError(){
        return "admin/error";
    }

    @RequestMapping("/index")
    public String toIndex (){
        return "admin/index";
    }

    @RequestMapping("/top.jsp")
    public String loadTop (){
        return "admin/top";
    }

    @RequestMapping("/main.jsp")
    public String loadMain (){
        return "admin/main";
    }

    @RequestMapping("/left.jsp")
    public String loadLeft (){
        return "admin/left";
    }

    @RequestMapping("/foot.jsp")
    public String loadFoot (){
        return "admin/foot";
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

//    public PageInfo selectAll() {
//        TestPage TestPage = new TestPage();
//        List<E> selectAll = TestMapper.selectAll(TestPage.enablePaging());
//        PageInfo<E> pageInfo = new PageInfo<>(selectAll);
//        return pageInfo;
//    }
}
