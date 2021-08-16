package org.openreading.readingisgood.model;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */

public class OrderTransaction extends Base{
    public OrderTransaction(){
    //Sub document ids' are not generated automatically
    super.setId(new ObjectId().toString());
    }

    private OrderStatus fromStatus;
    private OrderStatus toStatus;
    private Date transactionTime;


    public OrderStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(OrderStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public OrderStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(OrderStatus toStatus) {
        this.toStatus = toStatus;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
