package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Notice extends BaseModel {
    private String content;
    private Date createDate;

    public String getColumns() {
        return "id,name,content,create_date";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "','" + this.getContent() + "','" + this.getCreateDate() + "'";
    }
}
