package com.xk.lifebook.interfaceController;

import com.xk.lifebook.admin.model.User;
import com.xk.lifebook.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户接口控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/UserIn")
public class UserInterfaceController {
    @Autowired
    private UserService userService;

    @RequestMapping("/loginCheck")
    @ResponseBody
    public List<Map<String,Object>> loginCheck(HttpServletRequest request, String username, String password){
        User user = new User();
        user.setAccount(username);
        user.setPassword(password);
        List<Map<String,Object>> result = userService.loginCheck(user);
        return result;
    }
}
