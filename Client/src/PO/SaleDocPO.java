package PO;

import Util.DocType;

import java.io.Serializable;
import java.util.ArrayList;

public class SaleDocPO extends PurchaseSaleDocPO implements Serializable{

    private String salesmanId;
    private ArrayList<GiftItemPO> giftList;
    private double rebate;
    private int voucher;

    public SaleDocPO(String prKey, String operatorId,
                     DocType type, String customerId,
                     ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                     String salesmanId, String comment) {
        this(prKey, null, operatorId, type, customerId, itemList, salesmanId,
                null, 0.0, 0, comment);
    }

    public SaleDocPO(String prKey, String id, String operatorId,
                     DocType type, String customerId,
                     ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                     String salesmanId, String comment) {
        this(prKey, id, operatorId, type, customerId, itemList, salesmanId,
                null, 0.0, 0, comment);
    }

    public SaleDocPO(String prKey, String operatorId,
                     DocType type, String customerId,
                     ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                     String salesmanId, ArrayList<GiftItemPO> giftList,
                     double rebate, int voucher, String comment) {
        this(prKey, null, operatorId, type, customerId, itemList,
                salesmanId, giftList, rebate, voucher, comment);
    }

    public SaleDocPO(String prKey, String id, String operatorId,
                     DocType type, String customerId,
                     ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                     String salesmanId, ArrayList<GiftItemPO> giftList,
                     double rebate, int voucher, String comment) {
        super(prKey, id, operatorId, type, customerId, itemList, comment);
        this.salesmanId = salesmanId;
        this.giftList = giftList;
        this.rebate = rebate;
        this.voucher = voucher;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public ArrayList<GiftItemPO> getGiftList() {
        return giftList;
    }

    public double getRebate() {
        return rebate;
    }

    public int getVoucher() {
        return voucher;
    }
}
