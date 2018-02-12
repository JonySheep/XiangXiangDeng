package Util;

import java.io.Serializable;

public class PackageGoodItem implements Serializable{

    String goodID;
    int goodNumber;

    public PackageGoodItem(String goodID,int goodNumber) {
        this.goodID = goodID;
        this.goodNumber = goodNumber;
    }

    public String getGoodID() {
        return goodID;
    }

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodID(String goodID) {
        this.goodID = goodID;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }
}
