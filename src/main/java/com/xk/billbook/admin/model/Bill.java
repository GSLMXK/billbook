package com.xk.billbook.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xk.billbook.admin.common.base.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单实体
 * xiekuang
 * 2018/8/3
 */
public class Bill extends BaseModel {
    private String billTypeId;
    private BigDecimal money;
    private String description;
    private Integer creatorId;
    private Date billDate;

    public String getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(String billTypeId) {
        this.billTypeId = billTypeId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getColumns() {
        return "id,name,billType_id,money,description,creator_id,bill_date";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "'," + this.getBillTypeId() + "," + this.getMoney() + ",'" + this.getDescription() + "'," + this.getCreatorId() + ",'" + this.getBillDate()+"'";
    }
}
