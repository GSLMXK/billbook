package com.xk.billbook.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

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
        //获取账单统计
        return Base_URL+"/homePage";
    }


    public List<Map<String,Object>> getPageData(){
        return null;
    }
}
