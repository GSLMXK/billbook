package com.xk.billbook.admin.common.base.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper<E> {

    @Select("SELECT * FROM ${table} WHERE id = #{id}")
    E findById(@Param("table")String table, @Param("id")int id);

    @Select("SELECT * FROM ${table}")
    List<E> findByList(@Param("table")String table);

    /**
     * 通过参数查找
     * @param table  表名
     * @param selectParm 字段
     * @param condition 条件
     * @return
     */
    @Select("SELECT ${selectParm} FROM ${table} WHERE ${condition}")
    List<Map<String,Object>> findByParm(@Param("table")String table, @Param("selectParm")String selectParm, @Param("condition")String condition);

    @Select("${sql}")
    List<E>  findBySql(@Param("sql")String sql);

    @Insert("insert into ${table} (${columns}) values( ${values} )")
    Integer insertByParm(@Param("table")String table, @Param("columns")String colums, @Param("values")String values);

}
