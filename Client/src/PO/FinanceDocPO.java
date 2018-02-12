package PO;

import Util.DocType;

import java.io.Serializable;

public abstract class FinanceDocPO extends DocPO implements Serializable {

    public FinanceDocPO(String prKey, String id, String operatorId, DocType type) {
        super(prKey, id, operatorId, type);
    }
}
