package PO;

import Util.DocType;

import java.io.Serializable;
import java.util.ArrayList;

public class CashDocPO extends FinanceDocPO implements Serializable {

    private String account;
    private ArrayList<CashItemPO> itemList;

    public CashDocPO(String prKey, String operatorId, DocType type,
                     String account, ArrayList<CashItemPO> itemList) {
        this(prKey, null, operatorId, type, account, itemList);
    }

    public CashDocPO(String prKey, String id, String operatorId, DocType type,
                     String account, ArrayList<CashItemPO> itemList) {
        super(prKey, id, operatorId, type);
        this.account = account;
        this.itemList = itemList;
    }

    public String getAccount() {
        return account;
    }

    public ArrayList<CashItemPO> getItemList() {
        return itemList;
    }
}
