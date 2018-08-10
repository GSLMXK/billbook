package com.xk.billbook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.billbook.admin.common.base.model.PageBean;
import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.BillManagerMapper;
import com.xk.billbook.admin.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BillManagerService extends BaseService<Bill> {
    private final String TABLE = "bb_bill";
    @Autowired
    BillManagerMapper billMapper;

    //
    public Bill findById(int id) {
        String selectParm = "id,name,billType_id billTypeId,money,bill_date billDate,description";
        return (Bill) billMapper.findById(selectParm,TABLE,id);
    }

    //查询当前用户的全部账单
    public List<Map<String,Object>> findAll(int id){
        String selectParm = "bill.id,bill.name,bill.billType_id billTypeId,bill.money,u.name creatorName,bill.bill_date billDate,type.name typeName";
        String tableParm = "bb_bill bill left join bb_billType type on type.id = bill.billType_id left join bb_user u on u.id = bill.creator_id";
        String condition = "bill.creator_id = "+id;
        return  billMapper.findByParm(tableParm,selectParm,condition);
    }

    //插入新账单
    public Integer insertBill(Bill bill){
        return  billMapper.insertByParm(TABLE,bill.getColumns(),bill.getValues());
    }

    public Integer delById(int id) {
        return  billMapper.delById(TABLE,id);
    }

    public PageBean<Map<String,Object>> findByPage(int currentPage, int pageSize,int id) {
        String selectParm = "bill.id,bill.name,bill.billType_id billTypeId,bill.money,u.name creatorName,bill.bill_date billDate,type.name typeName";
        String tableParm = "bb_bill bill left join bb_billType type on type.id = bill.billType_id left join bb_user u on u.id = bill.creator_id";
        String condition = "bill.creator_id = "+id+"";
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String,Object>> allItems = billMapper.findByParm(tableParm,selectParm,condition);        //全部商品
        int countNums = billMapper.countList(tableParm,condition);            //总记录数
        PageBean<Map<String,Object>> pageData = new PageBean<Map<String,Object>>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
}
