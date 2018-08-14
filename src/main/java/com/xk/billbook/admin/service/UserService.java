package com.xk.billbook.admin.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xk.billbook.admin.common.base.service.BaseService;
import com.xk.billbook.admin.model.User;
import com.xk.billbook.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Repository
public class UserService extends BaseService {
    private final String TABLE = "bb_user";
    @Autowired
    UserMapper userMapper;

    public User selectUser(int id) {
        return (User)userMapper.findById(new User().getColumn(),TABLE,id);
    }


    public List<Map<String,Object>> loginCheck(User user){
        StringBuffer condition = new StringBuffer("account='"+user.getAccount());
        condition.append("' and password='"+user.getPassword()+"'");
        List<Map<String,Object>> result = userMapper.findByParm(TABLE,"*",condition.toString());
        return result;
    }

}
