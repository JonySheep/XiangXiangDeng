package VO;

import PO.CashItemPO;

/**
 * 现金费用单中的条目的VO.
 */
public class CashItemVO {

    private String name;
    private double amount;
    private String comment;

    public CashItemVO(String name, double amount, String comment) {
        this.name = name;
        this.amount = amount;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public CashItemPO toPO() {
        return new CashItemPO(name, amount, comment);
    }
}
