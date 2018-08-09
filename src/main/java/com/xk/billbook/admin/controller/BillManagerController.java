package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.service.BillManagerService;
import com.xk.billbook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    BillTypeMgrService billTypeMgrService;
    @RequestMapping("/{id}")
    public Bill findById (@PathVariable int id){
        return (Bill)billmgrService.findById(id);
    }

    @RequestMapping("/list")
    public String findAll (Map<String, Object> map){
        int id = 1;
        List<Map<String,Object>> billList = billmgrService.findAll(id);
        map.put("billList", billList);
        return Base_URL+"list";
    }

    @RequestMapping("/add")
    public String toAddPage (Map<String, Object> map){
        List typeList = billTypeMgrService.findByList();
        map.put("typeList", typeList);
        return Base_URL+"add";
    }

    @RequestMapping("/edit")
    public String toEditPage (Map<String, Object> map){
        Integer editId = Integer.valueOf((String) map.get("bill_id"));
        Bill bill = billmgrService.findById(editId);
        List typeList = billTypeMgrService.findByList();
        map.put("typeList", typeList);
        map.put("bill", bill);
        return Base_URL+"edit";
    }

    @RequestMapping("/save")
    public String save (Bill bill){
        Integer result = 0;
        result = billmgrService.insertBill(bill);
        if(result!=null&&result>0){
            return "redirect:/BillMgr/list";
        }
        return toError();
    }


}
