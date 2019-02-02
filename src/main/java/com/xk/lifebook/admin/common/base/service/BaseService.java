package com.xk.lifebook.admin.common.base.service;

import com.github.pagehelper.PageHelper;
import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.common.base.model.PageBean;
import com.xk.lifebook.admin.common.utils.NormalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public abstract class BaseService<E> {

    //获取Mapper
    public abstract BaseMapper getMapper();
    public abstract String getTable();

    /**
     * 查找实体
     *
     * @param id
     * @return
     */
    public E findById(int id) {
        return (E) getMapper().findById("*", getTable(), id);
    }

    /**
     * 查找实体
     *
     * @param id
     * @return
     */
    public E findById(String colums,int id) {
        return (E) getMapper().findById(colums, getTable(), id);
    }
    public List<E> findByList(String condition) {
        return getMapper().findByList(condition);
    }

    //插入新数据
    public Integer insertData(Map<String, Object> model) {
        StringBuffer colums = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            colums.append(entry.getKey() + ",");
//            if () {
//
//            }
        }

        return getMapper().insertByParm(getTable(), colums.toString(), values.toString());
    }

    //修改
    public Integer update(Map<String, Object> model) {
        StringBuffer colums = new StringBuffer();
        StringBuffer values = new StringBuffer();
        Integer id = null;
        //组装修改sql
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            colums.append(entry.getKey() +"="+ entry.getValue());
        }
//        if(id==null){
//            return 0;
//        }
        return getMapper().updateEntity(getTable(), colums.toString(), id);
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param id
     * @param parms
     * @return
     */
    public PageBean<Map<String, Object>> findByPage(int currentPage, int pageSize, int id, Map<String, Object> parms) {
        String selectParm = "*";
        String tableParm = getTable();
        String condition = combineCondition(parms);
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String, Object>> allItems = getMapper().findByParm(tableParm, selectParm, condition);        //全部账单
        int countNums = getMapper().countList(tableParm, condition);            //总记录数
        PageBean<Map<String, Object>> pageData = new PageBean<Map<String, Object>>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delById(int id) {
        return getMapper().delById(getTable(), id);
    }

    /**
     * 组装update Sql
     *
     * @param columns
     * @param values
     * @return
     */
    public String combineUpdateSql(String[] columns, String[] values) {
        StringBuffer sql = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i] + "=" + values[i] + ", ");
        }
        String result = new NormalUtils().subLastSymbol(sql.toString(), ",");
        return result;
    }

    /**
     * 简单组装 CONDITION 条件语句
     */
    public String combineCondition(Map<String, Object> parms) {
        StringBuffer condition = new StringBuffer();
        if (!StringUtils.isEmpty(parms.get("searchDate"))) {
            condition.append(" and DATE_FORMAT( bill.bill_date, '%Y-%m' ) = '" + parms.get("searchDate") + "'");
        } else {
            condition.append(" and DATE_FORMAT(bill.bill_date, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')");
        }
        if (!StringUtils.isEmpty(parms.get("searchContent"))) {
            condition.append(" and (bill.name like '%" + parms.get("searchContent") + "%' or bill.description like '%" + parms.get("searchContent") + "%')");
        }
        return condition.toString();
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
