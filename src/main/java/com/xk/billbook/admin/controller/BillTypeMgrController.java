package com.xk.billbook.admin.controller;

import com.xk.billbook.admin.common.base.controller.BaseController;
import com.xk.billbook.admin.common.base.model.PageBean;
import com.xk.billbook.admin.mapper.BillTypeMgrMapper;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.model.BillType;
import com.xk.billbook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 账单类型管理控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/BillType")
public class BillTypeMgrController extends BaseController<BillType> {

    private final String Base_URL = "admin/billTypeMgr/";
    @Autowired
    BillTypeMgrService billTypeMgrService;

    public List getTypeList() {
        return billTypeMgrService.findByList();
    }

    @RequestMapping("/list")
    public String findAll(Map<String, Object> map, Integer currentPage, Integer pageSize) {
        int id = 1;
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        PageBean<BillType> typePage = billTypeMgrService.findByPage(currentPage, pageSize, id);
//        List<Map<String,Object>> billList = billmgrService.findAll(id);
        map.put("typePage", typePage);
        return Base_URL + "list";
    }

    @RequestMapping("/add")
    public String toAddPage (Map<String, Object> map){
        return Base_URL+"add";
    }

    @RequestMapping("/edit/{id}")
    public String toEditPage (Map<String, Object> map, @PathVariable int id){
        BillType type = billTypeMgrService.findById(id);
        map.put("type", type);
        return Base_URL+"edit";
    }

    @RequestMapping("/delete/{id}")
    public String delData (Map<String, Object> map, @PathVariable int id){
        Integer result = billTypeMgrService.delById(id);
        if(result!=null&&result>0){
            return "redirect:/BillType/list";
        }
        return toError();
    }

    @RequestMapping("/save")
    public String save (BillType type){
        Integer result = 0;
        result = billTypeMgrService.insertBill(type);
        if(result!=null&&result>0){
            return "redirect:/BillType/list";
        }
        return toError();
    }
}
