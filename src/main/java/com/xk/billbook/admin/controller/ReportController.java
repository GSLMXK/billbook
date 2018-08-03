package com.xk.billbook.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统计查询控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/Report")
public class ReportController {
    private final String Base_URL = "admin/report";
    /**
     * 展示统计主页
     * @return
     */
    @RequestMapping("/homePage")
    public String toHomePage (){
        return Base_URL+"/homePage";
    }
}
