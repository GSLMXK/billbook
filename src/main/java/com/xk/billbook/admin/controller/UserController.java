package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.model.User;
import com.xk.billbook.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public String selectUser (@PathVariable int id){
        return userService.selectUser(id).toString();
    }


    @RequestMapping("/loginCheck")
    public String loginCheck(HttpServletRequest request, User user){
        List<Map<String,Object>> result = userService.loginCheck(user);
        if(result.size()==1){
            request.getSession(true).setAttribute("userId",result.get(0).get("id"));
            request.getSession(true).setAttribute("name",result.get(0).get("name"));
            return "redirect:/index";
        }
        return "admin/user/login";
    }

    @RequestMapping("/register")
    public String registerPage(){
        return "admin/user/register";
    }

    @RequestMapping("/userCenter")
    public String userCenterPage(HttpServletRequest request,Map<String, Object> map){
        Integer id = (Integer)request.getSession(true).getAttribute("userId");
        User user = userService.findById(id);
        map.put("user",user);
        return "admin/user/userCenter";
    }
}
