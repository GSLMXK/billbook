package com.xk.billbook.admin.common.base.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

}
