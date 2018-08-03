package com.xk.billbook.admin.common.base.mapper;

import com.xk.billbook.admin.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseMapper<E> {

    @Select("SELECT * FROM ${table} WHERE id = #{id}")
    E findById(@Param("table")String table, @Param("id")int id);
}
