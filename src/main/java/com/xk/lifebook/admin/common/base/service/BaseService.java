package com.xk.lifebook.admin.common.base.service;

import com.xk.lifebook.admin.common.utils.NormalUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public abstract class BaseService<E> {

//    abstract String getTable();
//    public E findById(int id) {
//        return (E)BaseMapper.findById(this.getTable(),id);
//    }

    /**
     * 组装update Sql
     * @param columns
     * @param values
     * @return
     */
    public String combineUpdateSql(String[] columns, String[] values){
        StringBuffer sql = new StringBuffer();
        for (int i=0;i<columns.length;i++){
            sql.append(columns[i]+"="+values[i]+", ");
        }
        String result = new NormalUtils().subLastSymbol(sql.toString(),",");
        return result;
    }

    /**
     * 简单组装 CONDITION 条件语句
     * @param colums
     * @param parmsName
     * @param parms
     * @return
     */
    public String combineCondition(String[] colums, String[] parmsName,Map<String,Object> parms){
        StringBuffer condition = new StringBuffer();
        for (int i = 0; i<parmsName.length; i++){

        }
        return condition.toString();
    }
}
