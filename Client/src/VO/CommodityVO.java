package VO;

import PO.CommodityPO;
import Util.EmptyValue;

import java.util.EnumMap;

public class CommodityVO {
    private String goodId = EmptyValue.getString();
    private String goodName = EmptyValue.getString();
    private String goodType= EmptyValue.getString();
    private int totalInbound = EmptyValue.getInteger();
    private int totalOutbound = EmptyValue.getInteger();
    private double totalSalesVolume = EmptyValue.getDouble();
    private double totalPurchaseVolume = EmptyValue.getDouble();

    public CommodityVO(String goodId, String goodName, String goodType, int totalInbound, int totalOutbound, double totalSalesVolume, double totalPurchaseVolume) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodType = goodType;
        this.totalInbound = totalInbound;
        this.totalOutbound = totalOutbound;
        this.totalSalesVolume = totalSalesVolume;
        this.totalPurchaseVolume = totalPurchaseVolume;
    }

    public CommodityVO(CommodityPO commodityPO){
        this.goodId = commodityPO.getGoodId();
        this.goodName = commodityPO.getGoodName();
        this.goodType = commodityPO.getGoodType();
        this.totalInbound = commodityPO.getTotalInbound();
        this.totalOutbound = commodityPO.getTotalOutbound();
        this.totalSalesVolume = commodityPO.getTotalSalesVolume();
        this.totalPurchaseVolume = commodityPO.getTotalPurchaseVolume();
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public int getTotalInbound() {
        return totalInbound;
    }

    public void setTotalInbound(int totalInbound) {
        this.totalInbound = totalInbound;
    }

    public int getTotalOutbound() {
        return totalOutbound;
    }

    public void setTotalOutbound(int totalOutbound) {
        this.totalOutbound = totalOutbound;
    }

    public double getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(int totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public double getTotalPurchaseVolume() {
        return totalPurchaseVolume;
    }

    public void setTotalPurchaseVolume(int totalPurchaseVolume) {
        this.totalPurchaseVolume = totalPurchaseVolume;
    }
}
