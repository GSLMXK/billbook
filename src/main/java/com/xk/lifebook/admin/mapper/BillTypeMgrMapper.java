package com.xk.lifebook.admin.mapper;

import com.xk.lifebook.admin.common.base.mapper.BaseMapper;
import com.xk.lifebook.admin.model.Bill;
import com.xk.lifebook.admin.model.BillType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillTypeMgrMapper extends BaseMapper<BillType> {
}
