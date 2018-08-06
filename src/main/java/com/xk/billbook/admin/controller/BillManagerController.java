package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.service.BillManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 账单管理控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/BillMgr")
public class BillManagerController extends BaseController {
    private final String Base_URL = "admin/billManager/";
    @Autowired
    BillManagerService  billmgrService;
    @RequestMapping("/{id}")
    public Bill findById (@PathVariable int id){
        return (Bill)billmgrService.findById(id);
    }

    @RequestMapping("/list")
    public String findAll (){
        int id = 1;
        List<Bill> list = billmgrService.findAll(id);
        
        return Base_URL+"list";
    }
}
