package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;

public class User extends BaseModel {
    private String account;
    private String password;
    private String photo;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getColumns() {
        return "id,name,account,password,photo";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "','" + this.getAccount() + "','" + this.getPassword() + "','"+this.getPhoto()+"'";
    }
}
