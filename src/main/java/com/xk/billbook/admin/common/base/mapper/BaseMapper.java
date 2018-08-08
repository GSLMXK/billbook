package com.xk.billbook.admin.common.base.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMapper<E> {

    @Select("SELECT * FROM ${table} WHERE id = #{id}")
    E findById(@Param("table")String table, @Param("id")int id);

    @Select("SELECT * FROM ${table}")
    List<E> findByList(@Param("table")String table);

    @Select("SELECT ${selectParm} FROM ${table} WHERE ${condition}")
    List<E>  findBySql(@Param("table")String table, @Param("selectParm")String selectParm, @Param("condition")String condition);

    @Insert("insert into ${table} (${columns}) values( ${values} )")
    Integer insertBySql(@Param("table")String table, @Param("columns")String colums, @Param("values")String values);
}
