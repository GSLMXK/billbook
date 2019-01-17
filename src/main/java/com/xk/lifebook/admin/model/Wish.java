package com.xk.lifebook.admin.model;

import com.xk.lifebook.admin.common.base.model.BaseModel;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Wish extends BaseModel {
    private BigDecimal money;
    private BigDecimal moneyIn;
    private Integer creatorId;
    private Integer isFinished;
    private Integer finishDate;
    private String comment;

    public String getColumns() {
        return "id,name,money,money_in,creator_id,comment,is_finished,create_date";
    }

    public String getValues() {
        return this.getId() + ",'" + this.getName() + "'," + this.getMoney() + "," + this.getMoneyIn() +  "," + this.getCreatorId() +  ",'" + this.getComment() + "',"+ this.getIsFinished() +  "," + (this.getCreateDate()==null?"DATE(CURDATE())":("'"+this.getCreateDate() + "'"));
    }
}
