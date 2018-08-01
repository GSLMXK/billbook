package com.xk.billbook.admin.service;

import com.xk.billbook.admin.model.User;
import com.xk.billbook.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectUser(int id) {
        return userMapper.selectUser(id);
    }

}
