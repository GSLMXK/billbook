package com.xk.billbook.admin.mapper;

import com.xk.billbook.admin.common.base.mapper.BaseMapper;
import com.xk.billbook.admin.model.Bill;
import com.xk.billbook.admin.model.BillType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillTypeMgrMapper extends BaseMapper<Bill> {
    @Select("SELECT * FROM ${table} WHERE creator_id = #{creator_id}")
    List<BillType> findAll(@Param("table")String table, @Param("creator_id")int creatorId);
}
