package PO;

import Util.DocType;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentDocPO extends FinanceDocPO implements Serializable {

    private String customerId;
    private ArrayList<AccountForDocPO> accountList;

    public PaymentDocPO(String prKey, String operatorId, DocType type,
                        String customerId, ArrayList<AccountForDocPO> accountList) {
        this(prKey, null, operatorId, type, customerId, accountList);
    }

    public PaymentDocPO(String prKey, String id, String operatorId, DocType type,
                        String customerId, ArrayList<AccountForDocPO> accountList) {
        super(prKey, id, operatorId, type);
        this.customerId = customerId;
        this.accountList = accountList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ArrayList<AccountForDocPO> getAccountList() {
        return accountList;
    }
}
