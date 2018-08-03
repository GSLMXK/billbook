package com.xk.billbook.admin.mapper;

import com.xk.billbook.admin.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BillManagerMapper {
    @Select("SELECT * FROM bb_user WHERE id = #{id}")
    User selectUser(int id);
}
