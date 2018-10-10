package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Plan extends BaseModel {
    private Integer isDefault;
    private String description;

    public String getColumns() {
        return "id,name,creator,create_date,is_default,description";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "','" + this.getCreatorId() + "','" + this.getCreateDate() + "','" + this.getIsDefault() + "','" + this.getDescription() + "'";
    }
}
