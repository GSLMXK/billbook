package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.model.Bill;
import com.xk.lifebook.admin.service.BillManagerService;
import com.xk.lifebook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 账单管理控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/BillMgr")
public class BillManagerController extends BaseController<Bill> {
    private final String Base_URL = "admin/billManager/";
    @Autowired
    BillManagerService billmgrService;
    @Autowired
    BillTypeMgrService billTypeMgrService;

    @Override
    public BaseService<Bill> getSevice() {
        return billmgrService;
    }

    //    @Override
    public String getBaseUrl() {
        return Base_URL;
    }

    @Override
    public String getControllerName() {
        return "BillMgr";
    }

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
            pageSize = 10;
        }
        String[] parmsName = {"searchContent","searchDate"};
        Map<String,Object> parms = getParms(request,parmsName);
        PageBean<Map<String,Object>> billPage = billmgrService.findByPage(currentPage, pageSize,id,parms);

        map.put("page", billPage);
        map.put("parms",parms);
        return Base_URL+"list";
    }


    @RequestMapping("/add")
    public String toAddPage (HttpServletRequest request,Map<String, Object> map){
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        Map<String,Object> typeList = billTypeMgrService.findByList(userId);
        map.put("typeList", typeList);
        return Base_URL+"add";
    }
    /**'
     * Ajax 获取当月账单统计数据
     * @return
     */
    @RequestMapping("/countData")
    @ResponseBody
    public Map<String,Object> getPageData(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        Integer id = (Integer)request.getSession(true).getAttribute("userId");
        result.put("moneyOI",billmgrService.getMonthData(id,request.getParameter("searchDate")));
        return result;
    }
    @RequestMapping("/edit/{id}")
    public String toEditPage (HttpServletRequest request,Map<String, Object> map, @PathVariable int id){
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        Bill bill = billmgrService.findById(id);
        Map<String,Object> typeList = billTypeMgrService.findByList(userId);
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
    public String save (HttpServletRequest request,Bill bill){
        Integer result = 0;
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        bill.setCreatorId(userId);
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
