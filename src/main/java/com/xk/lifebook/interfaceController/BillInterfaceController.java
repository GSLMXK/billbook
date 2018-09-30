package com.xk.lifebook.interfaceController;

import com.xk.lifebook.admin.service.BillManagerService;
import com.xk.lifebook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 账单接口控制类
 * xiekuang
 * 2018/8/3
 */
@Controller
@RequestMapping("/BillIn")
public class BillInterfaceController {
    @Autowired
    BillManagerService billmgrService;
    @Autowired
    BillTypeMgrService billTypeMgrService;
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
}
