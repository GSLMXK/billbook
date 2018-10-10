package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 资金规划明细
 */
@Data
public class PlanDetail extends BaseModel {
    private Integer planId;
    private Integer billTypeId;
    private BigDecimal money;

    public String getColumns() {
        return "id,name,plan_id,billType_id,money";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "','" + this.getPlanId() + "','" + this.getBillTypeId() + "','" + this.getMoney() + "'";
    }
}
