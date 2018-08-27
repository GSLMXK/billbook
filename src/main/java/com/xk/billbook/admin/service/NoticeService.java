package com.xk.billbook.admin.service;

import com.github.pagehelper.PageHelper;
import com.xk.billbook.admin.common.base.model.PageBean;
import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.mapper.NoticeMapper;
import com.xk.billbook.admin.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeService extends BaseService<Notice> {
    private final String TABLE = "bb_notice";
    @Autowired
    NoticeMapper noticeMapper;

    public List findByList(){
        return noticeMapper.findByList(TABLE);
    }

    public Notice findById(int id) {
        String selectParm = "id,name,content,create_date createDate";
        return (Notice) noticeMapper.findById(selectParm,TABLE,id);
    }

    public PageBean<Notice> findByPage(int currentPage, int pageSize, int id) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Notice> allItems = noticeMapper.findByList(TABLE);        //全部商品
        int countNums = noticeMapper.countList(TABLE," 1=1 ");            //总记录数
        PageBean<Notice> pageData = new PageBean<Notice>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
    //修改
    public Integer update(Notice notice) {
        String[] columns = notice.getColumns().split(",");
        String[] value = notice.getValues().split(",");
        //组装修改sql
        String values = combineUpdateSql(columns,value);
        return  noticeMapper.updateEntity(TABLE,values,notice.getId());
    }
}
