package com.xk.billbook.admin.common.base.model;

import lombok.Data;

/**
 * 基础Model类
 */
@Data
public class BaseModel {
    private Integer id;
    private String name;

    public String getColumn(){
        return "id,name";
    }
}
