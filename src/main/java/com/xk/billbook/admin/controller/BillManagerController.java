package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.common.base.model.PageBean;
import com.xk.billbook.admin.common.interceptor.Auth;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.service.BillManagerService;
import com.xk.billbook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String findAll (HttpServletRequest request, Map<String, Object> map, Integer currentPage, Integer pageSize){
        int id = (Integer) request.getSession(true).getAttribute("userId");
        if(currentPage == null){
            currentPage = 1;
        }
        if(pageSize == null){
            pageSize = 5;
        }
        PageBean<Map<String,Object>> billPage = billmgrService.findByPage(currentPage, pageSize,id);
//        List<Map<String,Object>> billList = billmgrService.findAll(id);
        map.put("page", billPage);
        return Base_URL+"list";
    }

    @RequestMapping("/add")
    public String toAddPage (Map<String, Object> map){
        List typeList = billTypeMgrService.findByList();
        map.put("typeList", typeList);
        return Base_URL+"add";
    }

    @RequestMapping("/edit/{id}")
    public String toEditPage (Map<String, Object> map, @PathVariable int id){
        Bill bill = billmgrService.findById(id);
        List typeList = billTypeMgrService.findByList();
        map.put("typeList", typeList);
        map.put("bill", bill);
        return Base_URL+"edit";
    }

    @RequestMapping("/delete/{id}")
    public String delData (Map<String, Object> map, @PathVariable int id){
        Integer result = billmgrService.delById(id);
        if(result!=null&&result>0){
            return "redirect:/BillMgr/list";
        }
        return toError();
    }

    @RequestMapping("/save")
    public String save (Bill bill){
        Integer result = 0;
        bill.setCreatorId(1);
        result = billmgrService.insertBill(bill);
        if(result!=null&&result>0){
            return "redirect:/BillMgr/list";
        }
        return toError();
    }
    @RequestMapping("/update")
    public String updateBill (Bill bill){
        Integer result = 0;
        result = billmgrService.update(bill);
        if(result!=null&&result>0){
            return "redirect:/BillMgr/list";
        }
        return toError();
    }



}
