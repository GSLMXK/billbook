package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.model.User;
import com.xk.lifebook.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    private final String Base_URL = "admin/user";

    @Override
    public BaseService getSevice() {
        return userService;
    }

    //    @Override
    public String getBaseUrl() {
        return Base_URL;
    }

    @Override
    public String getControllerName() {
        return "User";
    }

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
        map.put("properties",returnProperties());

        return "admin/user/userCenter";
    }
    @RequestMapping("/update")
    public String updateUser (User user){
        Integer result = 0;
        result = userService.update(user);
        if(result!=null&&result>0){
            return "redirect:/User/userCenter";
        }
        return toError();
    }
    @RequestMapping("/uploadPhoto")
    @ResponseBody
    public String uploadImg(@RequestParam("userPhoto") MultipartFile multipartFile, HttpServletRequest request)  {
        Integer result = 0;
        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
        }
        String contentType = multipartFile.getContentType();
        String root_fileName = multipartFile.getOriginalFilename();
        //处理图片
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        //获取路径
        String filePath = "user/"+userId+"/photo/";
        String file_name = "photo.jpg";
        try {
            file_name = saveFile(multipartFile, filePath, file_name);
            if(!StringUtils.isEmpty(file_name)){
                System.out.println("Success");
                User user = userService.findById(userId);
                user.setPhoto(filePath);
                result = userService.update(user);
                if(result!=null&&result>0){
                    return "Success";
                }
                return "Error";
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
