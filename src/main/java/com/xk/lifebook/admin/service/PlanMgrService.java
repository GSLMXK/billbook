package com.xk.lifebook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.mapper.PlanMgrMapper;
import com.xk.lifebook.admin.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
public class PlanMgrService extends BaseService<Plan> {
    private final String TABLE = "lb_plan";
    @Autowired
    PlanMgrMapper planMgrMapper;

    public List findByList(){
        return planMgrMapper.findByList(TABLE);
    }

    public Plan findById(int id) {
        String selectParm = "id,name,create_date createDate";
        return (Plan) planMgrMapper.findById(selectParm,TABLE,id);
    }
    //查询计划明细项
    public List<Map<String,Object>> findDetails(Integer id){
        String selectParm = "d.*,type.*";
        String tableParm = " lb_plan_detail d left join lb_billType type on d.billType_id = type.id ";
        String condition = " d.id = "+id+" order by d.id desc";
        return planMgrMapper.findByParm(tableParm,selectParm,condition);
    }
    public PageBean<Map<String,Object>> findByPage(int currentPage, int pageSize, int id, Map<String,Object> parms) {
        String selectParm = "plan.id,plan.name,plan.create_date createDate,plan.is_default isDefault, plan.description";
        String tableParm = "lb_plan plan ";
        String condition = "plan.creator_id = "+id+combineCondition(parms)+" order by plan.create_date desc";

        PageHelper.startPage(currentPage, pageSize);
        List<Map<String,Object>> allItems = planMgrMapper.findByParm(tableParm,selectParm,condition);
        int countNums = planMgrMapper.countList(TABLE," 1=1 ");            //总记录数
        PageBean<Map<String,Object>> pageData = new PageBean<Map<String,Object>>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
    public String combineCondition(Map<String,Object> parms){
        StringBuffer condition = new StringBuffer();
        if(!StringUtils.isEmpty(parms.get("searchContent"))){
            condition.append(" and (bill.name like '%"+parms.get("searchContent")+"%' or bill.description like '%"+parms.get("searchContent")+"%')");
        }
        return condition.toString();
    }
    //修改
    public Integer update(Plan plan) {
        StringBuffer values = new StringBuffer();
        values.append("id = "+plan.getId()+", content = '"+plan.getName()+"', create_date = '"+plan.getCreateDate()+"'");
        return  planMgrMapper.updateEntity(TABLE,values.toString(),plan.getId());
    }
}
