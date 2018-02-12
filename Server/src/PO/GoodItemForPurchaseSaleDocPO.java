package PO;

import java.io.Serializable;

public class GoodItemForPurchaseSaleDocPO implements Serializable {

    private String id;
    private int number;
    private double unitPrice;
    private String comment;

    public GoodItemForPurchaseSaleDocPO(String id, int number, double unitPrice, String comment) {
        this.id = id;
        this.number = number;
        this.unitPrice = unitPrice;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getComment() {
        return comment;
    }
}
