package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;

/**
 * 账单类型
 */
public class BillType extends BaseModel {
    private Integer type;
    private String comment;
    private Integer fid;
    private Integer creatorId;

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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getColumns() {
        return "id,name,type,comment,f_id,creator_id";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "'," + this.getType() + ",'" + this.getComment() + "','" + this.getFid() + "'," + this.getCreatorId();
    }
}
