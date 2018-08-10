package com.xk.billbook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.billbook.admin.common.base.model.PageBean;
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

    public BillType findById(int id) {
        String selectParm = "*";
        return (BillType) billTypeMgrMapper.findById(selectParm,TABLE,id);
    }

    public PageBean<BillType> findByPage(int currentPage, int pageSize, int id) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<BillType> allItems = billTypeMgrMapper.findByList(TABLE);        //全部商品
        int countNums = billTypeMgrMapper.countList(TABLE," 1=1 ");            //总记录数
        PageBean<BillType> pageData = new PageBean<BillType>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    public Integer delById(int id) {
        return  billTypeMgrMapper.delById(TABLE,id);
    }

    public Integer insertBill(BillType type){
        return  billTypeMgrMapper.insertByParm(TABLE,type.getColumns(),type.getValues());
    }
}
