package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单实体
 * xiekuang
 * 2018/8/3
 */
public class Bill extends BaseModel {
    private Integer billTypeId;
    private BigDecimal money;
    private Date billDate;

    public Integer getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(Integer billTypeId) {
        this.billTypeId = billTypeId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
