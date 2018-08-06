package com.xk.billbook.admin.common.base.service;

import com.xk.billbook.admin.common.base.mapper.BaseMapper;
import com.xk.billbook.admin.model.User;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseService<E> {

//    abstract String getTable();
//    public E findById(int id) {
//        return (E)BaseMapper.findById(this.getTable(),id);
//    }
}
