package Util;
import Presentation.CommodityUI.model.Good;


/**
 * 构成GoodSelector返回列表的类
 */
public class GoodSelectItem {
    private String id = EmptyValue.getString();
    private String name = EmptyValue.getString();
    private String fatherCategoryId = EmptyValue.getString();
    private String type = EmptyValue.getString();
    private Integer warningAmount = EmptyValue.getInteger();
    private Double buyingPrice = EmptyValue.getDouble();
    private Double sellingPrice = EmptyValue.getDouble();
    private String comment = EmptyValue.getString();
    private Integer nowAmount = EmptyValue.getInteger();
    private Double recentBuyingPrice = EmptyValue.getDouble();
    private Double recentSellingPrice = EmptyValue.getDouble();

    private Integer number = EmptyValue.getInteger();

    public GoodSelectItem(Good vo, Integer number) {
        this.id = vo.getId();
        this.name = vo.getName();
        this.fatherCategoryId = vo.getFatherCategoryId();
        this.type = vo.getType();
        this.warningAmount = vo.getWarningAmount();
        this.buyingPrice = vo.getBuyingPrice();
        this.sellingPrice = vo.getSellingPrice();
        this.comment = vo.getComment();
        this.nowAmount = vo.getNowAmount();
        this.recentBuyingPrice = vo.getRecentBuyingPrice();
        this.recentSellingPrice = vo.getRecentSellingPrice();
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFatherCategoryId() {
        return fatherCategoryId;
    }

    public String getType() {
        return type;
    }

    public Integer getWarningAmount() {
        return warningAmount;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public String getComment() {
        return comment;
    }

    public Integer getNowAmount() {
        return nowAmount;
    }

    public Double getRecentBuyingPrice() {
        return recentBuyingPrice;
    }

    public Double getRecentSellingPrice() {
        return recentSellingPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
