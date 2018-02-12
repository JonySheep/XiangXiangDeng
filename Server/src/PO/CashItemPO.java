package PO;

import java.io.Serializable;

public class CashItemPO implements Serializable {

    private String name;
    private double amount;
    private String comment;

    public CashItemPO(String name, double amount, String comment) {
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
}
