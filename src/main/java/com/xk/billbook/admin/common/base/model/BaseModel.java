package com.xk.billbook.admin.common.base.model;

/**
 * 基础Model类
 */
public class BaseModel {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'';
    }

    public String getColumn(){
        return "id,name";
    }
}
