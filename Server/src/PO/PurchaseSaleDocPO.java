package PO;

import Util.DocType;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PurchaseSaleDocPO extends DocPO implements Serializable {

    private String customerId;
    private ArrayList<GoodItemForPurchaseSaleDocPO> itemList;
    private String comment;

    public PurchaseSaleDocPO(String prKey, String id, String operatorId, DocType type,
                             String customerId,
                             ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                             String comment) {
        super(prKey, id, operatorId, type);
        this.customerId = customerId;
        this.itemList = itemList;
        this.comment = comment;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ArrayList<GoodItemForPurchaseSaleDocPO> getItemList() {
        return itemList;
    }

    public String getComment() {
        return comment;
    }
}
