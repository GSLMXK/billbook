package com.xk.billbook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.billbook.admin.common.base.model.PageBean;
import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.BillTypeMgrMapper;
import com.xk.billbook.admin.model.BillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillTypeMgrService extends BaseService<BillType> {
    private final String TABLE = "bb_billType";
    @Autowired
    BillTypeMgrMapper billTypeMgrMapper;

    public Map<String,Object> findByList(Integer userId){
        String parentCondition = TABLE+" WHERE creator_id = "+userId+" and f_id is null ORDER BY id DESC";
        List<BillType> plist =  billTypeMgrMapper.findByList(parentCondition);
        Map<String,Object> map = new HashMap<String,Object>();
        String childCondition = "";
        String parmName = "";
        map.put("parent",plist);
        for (int i = 0; i<plist.size(); i++){
            BillType model = plist.get(i);
            childCondition = TABLE+" WHERE creator_id = "+userId+" and f_id ="+model.getId()+" ORDER BY f_id DESC";
            parmName = model.getName();
            map.put(parmName,billTypeMgrMapper.findByList(childCondition));
        }
        return map;
    }

    public BillType findById(int id) {
        String selectParm = "id,name,type,comment,f_id as fid";
        return (BillType) billTypeMgrMapper.findById(selectParm,TABLE,id);
    }

    public List<Map<String,Object>> getFType() {
        String selectParm = "id,name,type,comment,f_id as fid";
        String condition = "f_id is null";
        return billTypeMgrMapper.findByParm(TABLE,selectParm,condition);
    }

    public PageBean<Map<String,Object>> findByPage(int currentPage, int pageSize, int userId) {
        String selectParm = "*";
        String condition = "creator_id = "+userId+" order by id desc";
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String,Object>> allItems = billTypeMgrMapper.findByParm(TABLE,selectParm,condition);        //用户账单类型
        int countNums = billTypeMgrMapper.countList(TABLE,condition);            //总记录数
        PageBean<Map<String,Object>> pageData = new PageBean<Map<String,Object>>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    public Integer delById(int id) {
        return  billTypeMgrMapper.delById(TABLE,id);
    }

    public Integer insertBill(BillType type){
        return  billTypeMgrMapper.insertByParm(TABLE,type.getColumns(),type.getValues());
    }


    //修改
    public Integer update(BillType type) {
        String[] columns = type.getColumns().split(",");
        String[] value = type.getValues().split(",");
        //组装修改sql
        String values = combineUpdateSql(columns,value);
        return  billTypeMgrMapper.updateEntity(TABLE,values,type.getId());
    }
}
