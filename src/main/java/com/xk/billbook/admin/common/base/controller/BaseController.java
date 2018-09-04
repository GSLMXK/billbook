package com.xk.billbook.admin.common.base.controller;

import com.xk.billbook.admin.common.base.mapper.BaseMapper;
import com.xk.billbook.admin.common.utils.NormalUtils;
import com.xk.billbook.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController<E> {

    private final String ERROR_URL = "admin/shopCart/error";
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

   /* @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> imgUpload3(MultipartFile upfile) {
        System.out.println("开始上传");
        Map<String, String> result = new HashMap<String, String>();
        String path = "";
        try {
            path = fastDFSClientWrapper.uploadFile(upfile);
        } catch (IOException e) {
            System.out.println("富文本框图片上传错误");
// e.printStackTrace();
        }
        System.out.println(path);
        File file = new File(path);
        result.put("url", path);
        result.put("size", String.valueOf(file.length()));
        result.put("type",
                file.getName().substring(file.getName().lastIndexOf(".")));
        result.put("state", "SUCCESS");

        return result;
    }*/
}
