package PO;

import java.io.Serializable;

public class GoodItemForGoodDocPO implements Serializable {

    private String id;
    private int change;

    public GoodItemForGoodDocPO(String id, int change) {
        this.id = id;
        this.change = change;
    }

    public String getId() {
        return id;
    }

    public int getChange() {
        return change;
    }
}
