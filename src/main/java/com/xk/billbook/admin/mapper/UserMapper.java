package com.xk.billbook.admin.mapper;

import com.xk.billbook.admin.common.base.mapper.BaseMapper;
import com.xk.billbook.admin.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}