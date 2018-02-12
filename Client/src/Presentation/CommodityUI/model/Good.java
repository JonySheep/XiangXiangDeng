package Presentation.CommodityUI.model;

import javafx.beans.property.*;

public class Good extends GoodTreeNode{

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty fatherCategoryId;
    private final StringProperty type;
    private final IntegerProperty warningAmount;
    private final DoubleProperty buyingPrice;
    private final DoubleProperty sellingPrice;

    private final IntegerProperty nowAmount;
    private final DoubleProperty recentBuyingPrice;
    private final DoubleProperty recentSellingPrice;

    private final StringProperty comment;

    public Good(String id, String name, String fatherCategoryId, String type, Integer warningAmount, Double buyingPrice, Double sellingPrice, Integer nowAmount, Double recentBuyingPrice, Double recentSellingPrice, String comment) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.fatherCategoryId = new SimpleStringProperty(fatherCategoryId);
        this.type = new SimpleStringProperty(type);
        this.warningAmount = new SimpleIntegerProperty(warningAmount);
        this.buyingPrice = new SimpleDoubleProperty(buyingPrice);
        this.sellingPrice = new SimpleDoubleProperty(sellingPrice);
        this.nowAmount = new SimpleIntegerProperty(nowAmount);
        this.recentBuyingPrice = new SimpleDoubleProperty(recentBuyingPrice);
        this.recentSellingPrice = new SimpleDoubleProperty(recentSellingPrice);
        this.comment = new SimpleStringProperty(comment);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFatherCategoryId() {
        return fatherCategoryId.get();
    }

    public void setFatherCategoryId(String fatherCategoryId) {
        this.fatherCategoryId.set(fatherCategoryId);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getWarningAmount() {
        return warningAmount.get();
    }

    public void setWarningAmount(int warningAmount) {
        this.warningAmount.set(warningAmount);
    }

    public double getBuyingPrice() {
        return buyingPrice.get();
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice.set(buyingPrice);
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    public int getNowAmount() {
        return nowAmount.get();
    }

    public void setNowAmount(int nowAmount) {
        this.nowAmount.set(nowAmount);
    }

    public double getRecentBuyingPrice() {
        return recentBuyingPrice.get();
    }

    public void setRecentBuyingPrice(double recentBuyingPrice) {
        this.recentBuyingPrice.set(recentBuyingPrice);
    }

    public double getRecentSellingPrice() {
        return recentSellingPrice.get();
    }

    public void setRecentSellingPrice(double recentSellingPrice) {
        this.recentSellingPrice.set(recentSellingPrice);
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

}
