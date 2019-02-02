package com.xk.lifebook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.mapper.BillManagerMapper;
import com.xk.lifebook.admin.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
public class BillManagerService extends BaseService<Bill> {
    private final String TABLE = "lb_bill";
    @Autowired
    private BillManagerMapper billMgrMapper;

    @Override
    public BaseMapper getMapper() {
        return billMgrMapper;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    //
    public Bill findById(int id) {
        String selectParm = "id,name,billType_id billTypeId,money,bill_date billDate,description, creator_id creatorId";
        return (Bill) billMgrMapper.findById(selectParm,TABLE,id);
    }

    //查询当前用户的全部账单
    public List<Map<String,Object>> findAll(int id){
        String selectParm = "bill.id,bill.name,bill.billType_id billTypeId,bill.money,u.name creatorName,bill.bill_date billDate,type.name typeName";
        String tableParm = "lb_bill bill left join lb_billType type on type.id = bill.billType_id left join lb_user u on u.id = bill.creator_id";
        String condition = "bill.creator_id = "+id;
        return  billMgrMapper.findByParm(tableParm,selectParm,condition);
    }

    //插入新账单
    public Integer insertBill(Bill bill){
        return  billMgrMapper.insertByParm(TABLE,bill.getColumns(),bill.getValues());
    }

    public Integer delById(int id) {
        return  billMgrMapper.delById(TABLE,id);
    }

    public PageBean<Map<String,Object>> findByPage(int currentPage, int pageSize, int id, Map<String,Object> parms) {
        String selectParm = "bill.id,bill.name,bill.billType_id billTypeId,bill.money,u.name creatorName,bill.bill_date billDate,type.name typeName";
        String tableParm = "lb_bill bill left join lb_billType type on type.id = bill.billType_id left join lb_user u on u.id = bill.creator_id";
        String condition = "bill.creator_id = "+id+combineCondition(parms)+" order by bill.bill_date desc, bill.id asc";
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String,Object>> allItems = billMgrMapper.findByParm(tableParm,selectParm,condition);        //全部账单
        int countNums = billMgrMapper.countList(tableParm,condition);            //总记录数
        PageBean<Map<String,Object>> pageData = new PageBean<Map<String,Object>>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
    public String combineCondition(Map<String,Object> parms){
        StringBuffer condition = new StringBuffer();
        if(!StringUtils.isEmpty(parms.get("searchDate"))){
            condition.append(" and DATE_FORMAT( bill.bill_date, '%Y-%m' ) = '"+parms.get("searchDate")+"'");
        }else{
            condition.append(" and DATE_FORMAT(bill.bill_date, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')");
        }
        if(!StringUtils.isEmpty(parms.get("searchContent"))){
            condition.append(" and (bill.name like '%"+parms.get("searchContent")+"%' or bill.description like '%"+parms.get("searchContent")+"%')");
        }
        return condition.toString();
    }
    //修改
    public Integer update(Bill bill) {
        String[] columns = bill.getColumns().split(",");
        String[] value = bill.getValues().split(",");
        //组装修改sql
        String values = combineUpdateSql(columns,value);
        return  billMgrMapper.updateEntity(TABLE,values,bill.getId());
    }
    //获取月收支信息
    public List<Map<String,Object>> getMonthData(Integer id,String month){
        String selectParm = "type.type, IFNULL(sum(bill.money),0) countMoney";
        String table = "lb_billType type LEFT JOIN (SELECT bill.billType_id,bill.money FROM lb_bill bill WHERE bill.creator_id = "+id+" and DATE_FORMAT( bill.bill_date, '%Y-%m' ) = '"+month+"' ) bill on bill.billType_id = type.id";
        String condition = " type.creator_id = "+id+" GROUP BY type.type ";
        return billMgrMapper.findByParm(table,selectParm,condition);
    }

}
