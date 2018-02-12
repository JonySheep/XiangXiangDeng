package PO;

import java.io.Serializable;

public class GiftItemPO implements Serializable{

    private String id;
    private int number;

    public GiftItemPO(String id,int number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }
}
