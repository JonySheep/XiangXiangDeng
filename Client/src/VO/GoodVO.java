package VO;

import PO.GoodPO;
import Util.EmptyValue;
import Util.GoodTreeNodeType;

public class GoodVO extends GoodTreeNodeVO{
    private String id = EmptyValue.getString();
    private String name = EmptyValue.getString();
    private String fatherCategoryId = EmptyValue.getString();
    private String type = EmptyValue.getString();
    private Integer warningAmount = EmptyValue.getInteger();
    private Double buyingPrice = EmptyValue.getDouble();
    private Double sellingPrice = EmptyValue.getDouble();
    private String comment = EmptyValue.getString();
    /**
     * 以下这三个数据由其他业务逻辑维护
     */
    private Integer nowAmount = EmptyValue.getInteger();
    private Double recentBuyingPrice = EmptyValue.getDouble();
    private Double recentSellingPrice = EmptyValue.getDouble();

    public GoodVO(String id, String name, String fatherCategoryId, String type, Integer warningAmount, Double buyingPrice, Double sellingPrice, String comment) {
        super.setNodeType(GoodTreeNodeType.GOOD);
        this.id = id;
        this.name = name;
        this.fatherCategoryId = fatherCategoryId;
        this.type = type;
        this.warningAmount = warningAmount;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.comment = comment;
    }

    public GoodVO(GoodPO goodPO) {
        super.setNodeType(GoodTreeNodeType.GOOD);
        this.id = goodPO.getId();
        this.name = goodPO.getName();
        this.fatherCategoryId = goodPO.getFatherCategoryId();
        this.type = goodPO.getType();
        this.warningAmount = goodPO.getWarningAmount();
        this.buyingPrice = goodPO.getBuyingPrice();
        this.sellingPrice = goodPO.getSellingPrice();
        this.warningAmount = goodPO.getWarningAmount();
        this.recentBuyingPrice = goodPO.getRecentBuyingPrice();
        this.recentSellingPrice = goodPO.getRecentSellingPrice();
    }

    public GoodVO(String id, String name, String fatherCategoryId, String type, Integer warningAmount, Double buyingPrice, String commment) {
    }

    public GoodVO(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherCategoryId() {
        return fatherCategoryId;
    }

    public void setFatherCategoryId(String fatherCategoryId) {
        this.fatherCategoryId = fatherCategoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWarningAmount() {
        return warningAmount;
    }

    public void setWarningAmount(Integer warningAmount) {
        this.warningAmount = warningAmount;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getNowAmount() {
        return nowAmount;
    }

    public void setNowAmount(Integer nowAmount) {
        this.nowAmount = nowAmount;
    }

    public Double getRecentBuyingPrice() {
        return recentBuyingPrice;
    }

    public void setRecentBuyingPrice(Double recentBuyingPrice) {
        this.recentBuyingPrice = recentBuyingPrice;
    }

    public Double getRecentSellingPrice() {
        return recentSellingPrice;
    }

    public void setRecentSellingPrice(Double recentSellingPrice) {
        this.recentSellingPrice = recentSellingPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
