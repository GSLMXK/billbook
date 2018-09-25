package com.xk.lifebook.admin.common.base.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper<E> {

    @Select("SELECT ${columns} FROM ${table} WHERE id = #{id}")
    E findById(@Param("columns")String colums,@Param("table")String table, @Param("id")int id);

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

    @Select("SELECT count(1) FROM ${table} WHERE ${condition}")
    Integer countList(@Param("table")String table, @Param("condition")String condition);

    @Insert("insert into ${table} (${columns}) values( ${values} )")
    Integer insertByParm(@Param("table")String table, @Param("columns")String colums, @Param("values")String values);

    @Delete("DELETE FROM ${table} WHERE id =#{id}")
    Integer delById(@Param("table")String table, @Param("id")int id);

    @Delete("DELETE FROM ${table} WHERE id in ( #{ids} )")
    Integer delByIds(@Param("table")String table, @Param("ids")String ids);

    @Update("UPDATE ${table} SET ${values} WHERE id=#{id}")
    Integer updateEntity(@Param("table")String table,@Param("values")String values, @Param("id")Integer id);
}
