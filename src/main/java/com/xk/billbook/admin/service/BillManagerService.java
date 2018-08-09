package com.xk.billbook.admin.service;

import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.BillManagerMapper;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.model.User;
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
        return (Bill) billMapper.findById(TABLE,id);
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

}
