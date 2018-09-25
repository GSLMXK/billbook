package com.xk.lifebook.admin.mapper;

import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.model.Bill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillManagerMapper extends BaseMapper<Bill> {
    @Select("SELECT * FROM ${table} WHERE creator_id = #{creator_id}")
    List<Bill> findAll(@Param("table")String table, @Param("creator_id")int creatorId);
}
