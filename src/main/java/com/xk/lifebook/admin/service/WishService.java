package com.xk.lifebook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.mapper.WishMapper;
import com.xk.lifebook.admin.model.Bill;
import com.xk.lifebook.admin.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Repository
public class WishService  extends BaseService<Wish> {
    private final String TABLE = "lb_wish";
    @Override
    public String getTable() {
        return TABLE;
    }
    @Autowired
    WishMapper wishMapper;
    public PageBean<Map<String,Object>> findByPage(int currentPage, int pageSize, int id, Map<String,Object> parms) {
        String selectParm = "w.*";
        String tableParm = "lb_wish w";
        String condition = "w.creator_id = "+id+combineCondition(parms)+" order by w.create_date desc";
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String,Object>> allItems = wishMapper.findByParm(tableParm,selectParm,condition);        //全部账单
        int countNums = wishMapper.countList(tableParm,condition);            //总记录数
        PageBean<Map<String,Object>> pageData = new PageBean<Map<String,Object>>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
    public String combineCondition(Map<String,Object> parms){
        StringBuffer condition = new StringBuffer();
        if(!StringUtils.isEmpty(parms.get("searchDate"))){
            condition.append(" and DATE_FORMAT( bill.bill_date, '%Y-%m' ) = '"+parms.get("searchDate")+"'");
        }
        if(!StringUtils.isEmpty(parms.get("searchContent"))){
            condition.append(" and (bill.name like '%"+parms.get("searchContent")+"%' or bill.description like '%"+parms.get("searchContent")+"%')");
        }
        return condition.toString();
    }
    //获取月收支信息
    public List<Map<String,Object>> getCountData(Integer id,String month){
        String selectParm = "convert(sum(bill.money),decimal(20,2)) all_save, convert((select SUM(money_in) from lb_wish where creator_id = "+id+"),decimal(20,2)) used";
        String table = "lb_bill bill LEFT JOIN lb_billType type on bill.billType_id = type.id";
        String condition = " bill.creator_id = "+id+" and type.type = 2 GROUP BY type.type ";
        return wishMapper.findByParm(table,selectParm,condition);
    }
    public Wish findById(int id) {
        String selectParm = "id, money_in moneyIn, money, comment, name, is_finished isFinished";
        return (Wish) wishMapper.findById(selectParm,TABLE,id);
    }
    //修改
    public Integer update(Wish wish) {
        String[] columns = wish.getColumns().split(",");
        String[] value = wish.getValues().split(",");
        //组装修改sql
        String values = combineUpdateSql(columns,value);
        return  wishMapper.updateEntity(TABLE,values,wish.getId());
    }
    public Integer insertWish(Wish wish){
        return  wishMapper.insertByParm(TABLE,wish.getColumns(),wish.getValues());
    }

    public Integer finishWish(Integer id) {
        String values = "is_finished = 1, finish_date = DATE(CURDATE())";
        return  wishMapper.updateEntity(TABLE,values,id);
    }
    public Integer delById(int id) {
        return  wishMapper.delById(TABLE,id);
    }
}
