package PO;

import Util.DocType;

import java.io.Serializable;
import java.util.ArrayList;

public class GoodDocPO extends DocPO {

    private ArrayList<GoodItemForGoodDocPO> itemList;
    private String comment;

    public GoodDocPO(String prKey, String operatorId, DocType type,
                     ArrayList<GoodItemForGoodDocPO> itemList, String comment) {
        this(prKey, null, operatorId, type, itemList, comment);
    }

    public GoodDocPO(String prKey, String id, String operatorId, DocType type,
                     ArrayList<GoodItemForGoodDocPO> itemList, String comment) {
        super(prKey, id, operatorId, type);
        this.itemList = itemList;
        this.comment = comment;
    }

    public ArrayList<GoodItemForGoodDocPO> getItemList() {
        return itemList;
    }

    public String getComment() {
        return comment;
    }

}
