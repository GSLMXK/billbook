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

    //
    public List findAll(int id){
        return  billMapper.findAll(TABLE,id);
    }
}
