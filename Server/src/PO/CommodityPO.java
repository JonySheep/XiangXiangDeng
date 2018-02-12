package PO;

import Util.EmptyValue;

import java.io.Serializable;

/**
 * Created by Jason on 2017/10/22.
 * Updated by Jason on 2017/11/11.
 */
public class CommodityPO implements Serializable {
    private String goodId = EmptyValue.getString();
    private String goodName = EmptyValue.getString();
    private String goodType = EmptyValue.getString();
    private int totalInbound = EmptyValue.getInteger();
    private int totalOutbound = EmptyValue.getInteger();
    private double totalSalesVolume = EmptyValue.getDouble();
    private double totalPurchaseVolume = EmptyValue.getDouble();

    /**
     * CommodityPO的构造器，CommodityPO参与库存变动列表的一条的组织
     * @param goodId 商品编号
     * @param goodName 商品名称
     * @param goodType 商品型号
     * @param totalInbound 入库总数量（用户选择的一段时间内）
     * @param totalOutbound 出库总数量（用户选择的一段时间内）
     * @param totalSalesVolume 销售总金额（用户选择的一段时间内）
     * @param totalPurchaseVolume 进货总金额（用户选择的一段时间内）
     */
    public CommodityPO(String goodId, String goodName, String goodType, int totalInbound, int totalOutbound, double totalSalesVolume, double totalPurchaseVolume) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodType = goodType;
        this.totalInbound = totalInbound;
        this.totalOutbound = totalOutbound;
        this.totalSalesVolume = totalSalesVolume;
        this.totalPurchaseVolume = totalPurchaseVolume;
    }

    /*
     * 以下是getter和setter
     */
    public String getGoodId() {
        return goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public String getGoodType() {
        return goodType;
    }

    public int getTotalInbound() {
        return totalInbound;
    }

    public int getTotalOutbound() {
        return totalOutbound;
    }

    public double getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public double getTotalPurchaseVolume() {
        return totalPurchaseVolume;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public void setTotalInbound(int totalInbound) {
        this.totalInbound = totalInbound;
    }

    public void setTotalOutbound(int totalOutbound) {
        this.totalOutbound = totalOutbound;
    }

    public void setTotalSalesVolume(double totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public void setTotalPurchaseVolume(double totalPurchaseVolume) {
        this.totalPurchaseVolume = totalPurchaseVolume;
    }
}
