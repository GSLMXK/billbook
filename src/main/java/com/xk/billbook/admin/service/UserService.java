package com.xk.billbook.admin.service;

import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.model.User;
import com.xk.billbook.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserService extends BaseService {
    private final String TABLE = "bb_user";
    @Autowired
    UserMapper userMapper;

    public User selectUser(int id) {
        return (User)userMapper.findById(new User().getColumn(),TABLE,id);
    }

}
