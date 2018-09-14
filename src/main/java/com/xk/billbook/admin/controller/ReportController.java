package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.model.ServerInfoVo;
import com.xk.billbook.admin.common.utils.SystemConfig;
import com.xk.billbook.admin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @Autowired
    private ReportService reportService;
    @Autowired
    private SystemConfig sysCfg;

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

    /**'
     * Ajax 获取首页统计数据
     * @return
     */
    @RequestMapping("/pageData")
    @ResponseBody
    public Map<String,Object> getPageData(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        Integer id = (Integer)request.getSession(true).getAttribute("userId");
        result.put("moneyOI",reportService.getPageData(id));
        return result;
    }


    /**'
     * Ajax 获取系统服务器数据
     * @return
     */
    @RequestMapping("/serverData")
    @ResponseBody
    public Map<String,Object> getSystemData(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        ServerInfoVo server = sysCfg.getServerInfo(request);
        result.put("server",server);
        return result;
    }


}
