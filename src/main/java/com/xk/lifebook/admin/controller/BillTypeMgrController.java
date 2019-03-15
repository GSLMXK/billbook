package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.model.BillType;
import com.xk.lifebook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    //    @Override
    public String getBaseUrl() {
        return Base_URL;
    }

    @Override
    public String getControllerName() {
        return "BillType";
    }

    @Autowired
    BillTypeMgrService billTypeMgrService;

    @Override
    public BaseService<BillType> getSevice() {
        return billTypeMgrService;
    }
    public Map<String,Object> getTypeList(HttpServletRequest request) {
        int id = (Integer) request.getSession(true).getAttribute("userId");
        return billTypeMgrService.findByList(id);
    }

    @RequestMapping("/list")
    public String findAll(HttpServletRequest request, Map<String, Object> map, Integer currentPage, Integer pageSize) {
        int id = (Integer) request.getSession(true).getAttribute("userId");
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageBean<Map<String,Object>> typePage = billTypeMgrService.findByPage(currentPage, pageSize, id);
//        List<Map<String,Object>> billList = billmgrService.findAll(id);
        map.put("page", typePage);
        return Base_URL + "list";
    }


    @RequestMapping("/add")
    public String toAddPage (HttpServletRequest request, Map<String, Object> map){
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        List<Map<String,Object>> fList = billTypeMgrService.getFType(userId);
        map.put("fList", fList);
        return Base_URL+"add";
    }

    @RequestMapping("/edit/{id}")
    public String toEditPage (HttpServletRequest request, Map<String, Object> map, @PathVariable int id){
        BillType type = billTypeMgrService.findById(id);
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        List<Map<String,Object>> fList = billTypeMgrService.getFType(userId);
        map.put("fList", fList);
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
    public String save (HttpServletRequest request,BillType type){
        Integer result = 0;
        type.setCreatorId((Integer)request.getSession().getAttribute("userId"));
        result = billTypeMgrService.insertBill(type);
        if(result!=null&&result>0){
            return "redirect:/BillType/list";
        }
        return toError();
    }

    @RequestMapping("/update")
    public String updateBill (HttpServletRequest request,BillType type){
        Integer result = 0;
        type.setCreatorId((Integer)request.getSession().getAttribute("userId"));
        result = billTypeMgrService.update(type);
        if(result!=null&&result>0){
            return "redirect:/BillType/list";
        }
        return toError();
    }
}
