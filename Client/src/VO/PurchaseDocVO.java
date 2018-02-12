package VO;

import PO.GoodItemForPurchaseSaleDocPO;
import PO.PurchaseDocPO;
import Presentation.DocUI.CheckingDoc.Previewable;
import Util.DocType;

import java.util.ArrayList;

/**
 * 进货类单据的VO.
 */
public class PurchaseDocVO extends PurchaseSaleDocVO implements Previewable{

    public PurchaseDocVO(String prKey, UserForDocVO operator,
                         DocType type, CustomerForDocVO customer,
                         ArrayList<GoodItemForPurchaseSaleDocVO> itemList, String comment) {
        this(prKey, null, operator, type, customer, itemList, comment);
    }

    public PurchaseDocVO(String prKey, String id, UserForDocVO operator,
                         DocType type, CustomerForDocVO customer,
                         ArrayList<GoodItemForPurchaseSaleDocVO> itemList,
                         String comment) {
        super(prKey, id, operator, type, customer, itemList, comment);
    }

    public PurchaseDocPO toPO() {
        ArrayList<GoodItemForPurchaseSaleDocPO> itemPOList = new ArrayList<>();
        for (GoodItemForPurchaseSaleDocVO item : getItemList())
            itemPOList.add(item.toPO());
        return new PurchaseDocPO(getPrKey(), getId(), getOperator().getId(),
                getType(), getCustomer().getId(), itemPOList, getComment());
    }

    @Override
    public DocType getDocType() {
        return getType();
    }

    @Override
    public String getName() {
        return getCustomer().getName();
    }
}
