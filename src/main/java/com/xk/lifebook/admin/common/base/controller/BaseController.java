package com.xk.lifebook.admin.common.base.controller;

import com.xk.lifebook.admin.common.utils.NormalUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController<E> {

    private final String ERROR_URL = "admin/error";
    private NormalUtils normalUtils = new NormalUtils();
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${file.location}")
    private String location;
    //返回错误页
    public String toError(){
        return "admin/error";
    }

    //返回各种静态数据
    public Map<String,String> returnProperties(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("location",location);
        return map;
    }
    //保存文件
    public String saveFile(MultipartFile multipartFile, String filePath, String fileName){
        String result = "";
        try {
            filePath = location+filePath;
            result = normalUtils.saveFile(multipartFile, filePath,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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

    public Map<String, Object> getParms(HttpServletRequest request, String[] parmNames){
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i= 0; i<parmNames.length; i++){
            map.put(parmNames[i],request.getParameter(parmNames[i]));
        }
        return map;
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
