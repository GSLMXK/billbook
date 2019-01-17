package com.xk.lifebook.admin.service;

import com.xk.lifebook.admin.common.base.service.BaseService;
import com.xk.lifebook.admin.mapper.UserMapper;
import com.xk.lifebook.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserService extends BaseService {
    private final String TABLE = "lb_user";
    @Autowired
    UserMapper userMapper;
    @Override
    public String getTable() {
        return TABLE;
    }
    public User selectUser(int id) {
        return (User)userMapper.findById(new User().getColumn(),TABLE,id);
    }

    public User findById(int id) {
        String selectParm = "id,name,account,password, photo";
        return (User) userMapper.findById(selectParm,TABLE,id);
    }
    public List<Map<String,Object>> loginCheck(User user){
        StringBuffer condition = new StringBuffer("account='"+user.getAccount());
        condition.append("' and password='"+user.getPassword()+"'");
        List<Map<String,Object>> result = userMapper.findByParm(TABLE,"*",condition.toString());
        return result;
    }
    public Integer update(User user) {
        String[] columns = user.getColumns().split(",");
        String[] value = user.getValues().split(",");
        //组装修改sql
        String values = combineUpdateSql(columns,value);
        return  userMapper.updateEntity(TABLE,values,user.getId());
    }
}
