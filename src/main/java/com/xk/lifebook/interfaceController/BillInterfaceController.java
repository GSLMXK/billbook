package com.xk.lifebook.interfaceController;

import com.xk.lifebook.admin.model.Bill;
import com.xk.lifebook.admin.service.BillManagerService;
import com.xk.lifebook.admin.service.BillTypeMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**'
     * 获取当月账单统计数据
     * @return
     */
    @RequestMapping("/countData")
    @ResponseBody
    public Map<String,Object> getPageData(HttpServletRequest request,Integer id, String searchDate){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("moneyOI",billmgrService.getMonthData(id,searchDate));
        return result;
    }

    /**'
     * 获取账单类型
     * @return
     */
    @RequestMapping("/getBillType")
    @ResponseBody
    public Map<String,Object> getBillType(HttpServletRequest request,Integer userId){
        Map<String,Object> typeList = billTypeMgrService.findByList(userId);
        return typeList;
    }
    /**'
     * 保存账单
     * @return
     */
    @RequestMapping("/saveBill")
    @ResponseBody
    public String saveBill(HttpServletRequest request,String name,String billTypeId, String money, String description, String billDate,Integer userId){
        Integer result = 0;
        Bill bill = new Bill();
        bill.setCreatorId(userId);
        bill.setName(name);
        bill.setDescription(description);
        bill.setBillTypeId(Integer.valueOf(billTypeId));
        try {
            bill.setBillDate(sdf.parse(billDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result = billmgrService.insertBill(bill);
        if(result!=null&&result>0){
            return "Bill save success!";
        }
        return "Bill save failed";
    }
}
