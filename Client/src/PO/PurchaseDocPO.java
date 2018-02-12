package PO;

import Util.DocType;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchaseDocPO extends PurchaseSaleDocPO implements Serializable {

    public PurchaseDocPO(String prKey, String operatorId,
                         DocType type, String customerId,
                         ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                         String comment) {
        this(prKey, null, operatorId, type, customerId, itemList, comment);
    }

    public PurchaseDocPO(String prKey, String id, String operatorId,
                         DocType type, String customerId,
                         ArrayList<GoodItemForPurchaseSaleDocPO> itemList,
                         String comment) {
        super(prKey, id, operatorId, type, customerId, itemList, comment);
    }
}
