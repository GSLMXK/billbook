package com.xk.billbook.admin.model;

import com.xk.billbook.admin.common.base.model.BaseModel;

/**
 * 账单类型
 */
public class BillType extends BaseModel {
    private Integer type;
    private String comment;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColumns() {
        return "id,name,type,comment";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "'," + this.getType() + ",'" + this.getComment() + "'";
    }
}
