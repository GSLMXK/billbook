package com.xk.billbook.admin.service;

import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.BillTypeMgrMapper;
import com.xk.billbook.admin.model.BillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillTypeMgrService extends BaseService<BillType> {
    private final String TABLE = "bb_billType";
    @Autowired
    BillTypeMgrMapper billTypeMgrMapper;

    public List findByList(){
        return billTypeMgrMapper.findByList(TABLE);
    }
}
