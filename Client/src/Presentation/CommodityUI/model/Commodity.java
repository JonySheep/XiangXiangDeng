package Presentation.CommodityUI.model;


import javafx.beans.property.*;

public class Commodity {
    private StringProperty goodId;
    private StringProperty goodName;
    private StringProperty goodType;
    private IntegerProperty totalInbound;
    private IntegerProperty totalOutbound;
    private DoubleProperty totalSalesVolume;
    private DoubleProperty totalPurchaseVolume;

    public Commodity(String goodId, String goodName, String goodType, Integer totalInbound, Integer totalOutbound, Double totalSalesVolume, Double totalPurchaseVolume) {
        this.goodId = new SimpleStringProperty(goodId);
        this.goodName = new SimpleStringProperty(goodName);
        this.goodType = new SimpleStringProperty(goodType);
        this.totalInbound = new SimpleIntegerProperty(totalInbound);
        this.totalOutbound = new SimpleIntegerProperty(totalOutbound);
        this.totalSalesVolume = new SimpleDoubleProperty(totalSalesVolume);
        this.totalPurchaseVolume = new SimpleDoubleProperty(totalPurchaseVolume);
    }

    public String getGoodId() {
        return goodId.get();
    }
    public void setGoodId(String goodId) {
        this.goodId.set(goodId);
    }

    public String getGoodName() {
        return goodName.get();
    }

    public void setGoodName(String goodName) {
        this.goodName.set(goodName);
    }

    public String getGoodType() {
        return goodType.get();
    }

    public void setGoodType(String goodType) {
        this.goodType.set(goodType);
    }

    public int getTotalInbound() {
        return totalInbound.get();
    }

    public void setTotalInbound(int totalInbound) {
        this.totalInbound.set(totalInbound);
    }

    public int getTotalOutbound() {
        return totalOutbound.get();
    }

    public void setTotalOutbound(int totalOutbound) {
        this.totalOutbound.set(totalOutbound);
    }

    public double getTotalSalesVolume() {
        return totalSalesVolume.get();
    }

    public void setTotalSalesVolume(double totalSalesVolume) {
        this.totalSalesVolume.set(totalSalesVolume);
    }

    public double getTotalPurchaseVolume() {
        return totalPurchaseVolume.get();
    }

    public void setTotalPurchaseVolume(double totalPurchaseVolume) {
        this.totalPurchaseVolume.set(totalPurchaseVolume);
    }
}
