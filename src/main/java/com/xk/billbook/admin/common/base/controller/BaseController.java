package com.xk.billbook.admin.common.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaseController {
    private final String ERROR_URL = "admin/shopCart/list";
    @RequestMapping("/showUser/error")
    public String selectUser (@PathVariable int id){
        return null;
    }
}
