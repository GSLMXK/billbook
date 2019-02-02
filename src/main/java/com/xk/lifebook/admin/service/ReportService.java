package com.xk.lifebook.admin.service;

import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class ReportService extends BaseService {
    private final String TABLE = "";
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public BaseMapper getMapper() {
        return reportMapper;
    }

    @Override
    public String getTable() {
        return TABLE;
    }
    //获取首页统计数据
    public List<Map<String, Object>> getPageData(Integer id) {
        String selectParm = "type.type, sum(bill.money) countMoney";
        String table = "lb_bill bill left join lb_billType type on bill.billType_id = type.id ";
        String condition = " bill.creator_id = " + id + " and DATE_FORMAT( bill.bill_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) GROUP BY type.type ";
        return reportMapper.findByParm(table, selectParm, condition);
    }
    //获取详细统计数据
    public List<Map<String, Object>> getReportData(Integer userId, Map<String,Object> parms) {
        String type = (String) parms.get("type");
        String month = parms.get("month")!=null?"'"+(String) parms.get("month")+"'":"DATE_FORMAT( CURDATE( ) , '%Y-%m' )";
        String selectParm = "sum(bill.money) countMoney,type.name typeName,parType.name pTypeName ";
        String table = " lb_bill bill left join lb_billType type on type.id = bill.billType_id " +
                        " left join lb_billType parType on parType.id = type.f_id";
        String condition = " bill.creator_id = "+userId+" and DATE_FORMAT( bill.bill_date, '%Y-%m' ) = "+month+" and type.type = "+type+" group by bill.billType_id order by parType.name";
        return reportMapper.findByParm(table, selectParm, condition);
    }
}
