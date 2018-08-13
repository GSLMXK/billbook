package com.xk.billbook.admin.common.base.service;

import com.xk.billbook.admin.common.utils.NormalUtils;
import org.springframework.stereotype.Repository;

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
}
