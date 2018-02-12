package PO;

import Util.DocType;

import java.io.Serializable;

public abstract class DocPO implements Serializable {

    private String prKey;
    private String id;
    private String operatorId;
    private DocType type;

    public DocPO(String prKey, String id, String operatorId, DocType type) {
        this.prKey = prKey;
        this.id = id;
        this.operatorId = operatorId;
        this.type = type;
    }

    public String getPrKey() {
        return prKey;
    }

    public String getId() {
        return id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public DocType getType() {
        return type;
    }
}
