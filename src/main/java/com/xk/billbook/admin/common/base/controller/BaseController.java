package com.xk.billbook.admin.common.base.controller;

import com.xk.billbook.admin.common.base.mapper.BaseMapper;
import com.xk.billbook.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class BaseController<E> {

    private final String ERROR_URL = "admin/shopCart/error";

    //返回错误页
    public String toError(){
        return "admin/error";
    }


    @RequestMapping("/index")
    public String toIndex (HttpServletRequest request, Map<String, Object> map){
        map.put("username", request.getSession(true).getAttribute("name"));
        return "admin/index";
    }
    @RequestMapping("/login")
    public String loginPage(){
        return "admin/user/login";
    }
    @RequestMapping("/logout")
    public String userLogout (HttpServletRequest request, Map<String, Object> map){
        request.getSession(true).removeAttribute("userId");
        return loginPage();
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
