package com.xk.lifebook.admin.common.base.model;

import lombok.Data;

import java.util.Date;

/**
 * 基础Model类
 */
@Data
public class BaseModel {
    private Integer id;
    private String name;
    private Integer creatorId;
    private Date createDate;
    private String description;

    public String getColumn(){
        return "id,name,creatorId,createDate,description";
    }
}
