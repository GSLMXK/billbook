package com.xk.lifebook.admin.controller;

import com.xk.lifebook.admin.common.base.controller.BaseController;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.model.Plan;
import com.xk.lifebook.admin.service.BillTypeMgrService;
import com.xk.lifebook.admin.service.PlanMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/PlanMgr")
public class PlanMgrController extends BaseController<Plan> {
    private final String Base_URL = "admin/planMgr/";
    @Autowired
    PlanMgrService planService;
    @Override
    public BaseService<Plan> getSevice() {
        return planService;
    }

    //    @Override
    public String getBaseUrl() {
        return Base_URL;
    }

    @Autowired
    BillTypeMgrService billTypeMgrService;
    @RequestMapping("/list")
    public String findAll(HttpServletRequest request, Map<String, Object> map, Integer currentPage, Integer pageSize) {
        int id = (Integer) request.getSession(true).getAttribute("userId");
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        String[] parmsName = {"searchContent"};
        Map<String,Object> parms = getParms(request,parmsName);
        PageBean<Map<String,Object>> typePage = planService.findByPage(currentPage, pageSize,id,parms);
//        List<Map<String,Object>> billList = billmgrService.findAll(id);
        map.put("page", typePage);
        return Base_URL + "list";
    }

    @RequestMapping("/edit/{id}")
    public String toEditPage (HttpServletRequest request, Map<String, Object> map, @PathVariable int id){
        int userId = (Integer) request.getSession(true).getAttribute("userId");
        Plan plan = planService.findById(id);
        List<Map<String,Object>> details = planService.findDetails(id);
        Map<String,Object> typeList = billTypeMgrService.findByList(userId);
        map.put("plan", plan);
        map.put("details", details);
        return Base_URL+"edit";
    }

    @RequestMapping("/add")
    public String toAddPage (Map<String, Object> map){
        return Base_URL+"add";
    }

    @RequestMapping("/update")
    public String updateBill (Plan notice){
        Integer result = 0;
        result = planService.update(notice);
        if(result!=null&&result>0){
            return "redirect:/Notice/list";
        }
        return toError();
    }
}
