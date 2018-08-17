package com.xk.billbook.admin.service;

import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class ReportService extends BaseService {
    @Autowired
    ReportMapper reportMapper;
       public List<Map<String,Object>> getPageData(Integer id){
           String selectParm = "type.type, sum(bill.money) countMoney";
           String table = "bb_bill bill left join bb_billType type on bill.billType_id = type.id ";
           String condition = " bill.creator_id = "+id+" GROUP BY type.type ";
           return reportMapper.findByParm(table,selectParm,condition);
       }
}
