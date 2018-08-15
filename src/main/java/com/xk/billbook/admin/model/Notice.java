package com.xk.billbook.admin.model;

import com.xk.billbook.admin.common.base.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Notice extends BaseModel {
    private String content;
    private Date createDate;

    public String getColumns() {
        return "id,name,billType_id,money,description,creator_id,bill_date";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "','" + this.getContent() + "','" + this.getCreateDate() + "'";
    }
}
