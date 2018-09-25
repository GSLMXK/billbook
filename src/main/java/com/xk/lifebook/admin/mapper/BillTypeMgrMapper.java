package com.xk.lifebook.admin.mapper;

import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.model.BillType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillTypeMgrMapper extends BaseMapper<BillType> {
    @Select("SELECT * FROM ${table} WHERE creator_id = #{creator_id}")
    List<BillType> findAll(@Param("table")String table, @Param("creator_id")int creatorId);
}
