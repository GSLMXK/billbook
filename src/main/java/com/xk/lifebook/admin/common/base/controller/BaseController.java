package com.xk.lifebook.admin.common.base.controller;

import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.common.utils.NormalUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Controller
public abstract class BaseController<E> {
    public abstract BaseService<E> getSevice();
    private final String ERROR_URL = "admin/error";
    private NormalUtils normalUtils = new NormalUtils();
    //获取基础路径
    public abstract String getBaseUrl();
    //获取子类控制器名
    public abstract String getControllerName();
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

    public Date getNowDate(){
        return normalUtils.getNowDate();
    }
    public Date getNowDateShort(){
        return normalUtils.getNowDateShort();
    }
    public String findAll (HttpServletRequest request, Map<String, Object> map, Integer currentPage, Integer pageSize){
        int id = (Integer) request.getSession(true).getAttribute("userId");
        if(currentPage == null){
            currentPage = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        String[] parmsName = {"searchContent","searchDate"};
        Map<String,Object> parms = getParms(request,parmsName);
        PageBean<Map<String,Object>> billPage = getSevice().findByPage(currentPage, pageSize,id,parms);

        map.put("page", billPage);
        map.put("parms",parms);
        return getBaseUrl()+"list";
    }
    public String toAddPage (HttpServletRequest request, Map<String, Object> map){
        return getBaseUrl()+"add";
    }

    public String toEditPage (HttpServletRequest request, Map<String, Object> map, @PathVariable int id){
        E model = getSevice().findById(id);
        map.put("model", model);
        return getBaseUrl()+"edit";
    }

    public String delData (Map<String, Object> map, @PathVariable int id){
        Integer result = getSevice().delById(id);
        if(result!=null&&result>0){
            return "redirect:/"+getControllerName()+"/list";
        }
        return toError();
    }
    @RequestMapping("/saveModel")
    public String saveModel (HttpServletRequest request,Map<String, Object> model){
        Integer result = 0;
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        result = getSevice().insertData(model);
        if(result!=null&&result>0){
            return "redirect:/"+getControllerName()+"/list";
        }
        return toError();
    }
    @RequestMapping("/updateModel")
    public String updateModel (Map<String, Object> model){
        Integer result = 0;
        result = getSevice().update(model);
        if(result!=null&&result>0){
            return "redirect:/"+getControllerName()+"/list";
        }
        return toError();
    }
    public Map<String, Object> getParms(HttpServletRequest request, String[] parmNames){
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i= 0; i<parmNames.length; i++){
            map.put(parmNames[i],request.getParameter(parmNames[i]));
        }
        return map;
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


}
