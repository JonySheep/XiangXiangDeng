package Util;

import PO.GiftItemPO;

import java.io.Serializable;

public class GiftItem implements Serializable{

    String  goodName;
    String goodID;
    int number;

    public GiftItem(String goodName, int number) {
        this.goodName=goodName;
        this.number = number;
    }

    public GiftItem(String goodName, String id, int number) {
        this.goodName=goodName;
        this.number = number;
        this.goodID=id;
    }

    //getter
    public String getGoodID(){return goodID;}
    public String getGoodName(){return goodName;}
    public int getNumber() {
        return number;
    }

    //setter

    public void setNumber(int number) {
        this.number = number;
    }

    public GiftItemPO toPO() {
        return new GiftItemPO(goodID, number);
    }
}
