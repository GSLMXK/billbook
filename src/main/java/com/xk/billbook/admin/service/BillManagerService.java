package com.xk.billbook.admin.service;

import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.BillManagerMapper;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillManagerService extends BaseService<Bill> {
    private final String TABLE = "bb_bill";
    @Autowired
    BillManagerMapper billMapper;

    //
    public Bill findById(int id) {
        return (Bill) billMapper.findById(TABLE,id);
    }

    //查询当前用户的全部账单
    public List findAll(int id){
        String selectParm = "id,name,billType_id billTypeId,money,creator_id creatorId,bill_date billDate";
        String condition = "creator_id = "+id;
        return  billMapper.findBySql(TABLE,selectParm,condition);
    }

    //插入新账单
    public Boolean insertBill(Bill bill){
        return  billMapper.insertBySql(TABLE,bill.getColumns(),bill.getValues());
    }

}
