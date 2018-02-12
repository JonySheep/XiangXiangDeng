package VO;

import PO.GoodItemForPurchaseSaleDocPO;

/**
 * 进货类、销售类单据中的商品属性的VO.
 */
public class GoodItemForPurchaseSaleDocVO {

    private String id;
    private String name;
    private String type;

    private int stockAmount;
    private int number;
    private double unitPrice;
    private String comment;

    public GoodItemForPurchaseSaleDocVO(String id, String name, String type, int stockAmount,
                                        int number, double unitPrice, String comment) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stockAmount = stockAmount;
        this.number = number;
        this.unitPrice = unitPrice;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return number * unitPrice;
    }

    public String getComment() {
        return comment;
    }

    public GoodItemForPurchaseSaleDocPO toPO() {
        return new GoodItemForPurchaseSaleDocPO(id, number, unitPrice, comment);
    }
}
