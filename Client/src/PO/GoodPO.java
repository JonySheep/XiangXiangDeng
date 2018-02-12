package PO;


import Util.EmptyValue;
import Util.GoodTreeNodeType;

import java.io.Serializable;

/**
 * Created by Jason on 2017/10/22.
 * Updated by Jason on 2017/11/09.
 */
public class GoodPO extends GoodTreeNodePO implements Serializable{
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

    /**
     * GoodPO的构造器
     * @param id 商品编号
     * @param name 商品名称
     * @param fatherCategoryId 商品父分类编号
     * @param type 商品型号
     * @param warningAmount 商品警戒数量
     * @param buyingPrice 商品进价
     * @param sellingPrice 商品售价
     */
    public GoodPO(String id, String name, String fatherCategoryId, String type, Integer warningAmount, Double buyingPrice,
                  Double sellingPrice,Integer nowAmount,Double recentBuyingPrice,Double recentSellingPrice, String comment) {
        super.setNodeType(GoodTreeNodeType.GOOD);

        this.id = id;
        this.name = name;
        this.fatherCategoryId = fatherCategoryId;
        this.type = type;
        this.warningAmount = warningAmount;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.nowAmount = nowAmount;
        this.recentBuyingPrice = recentBuyingPrice;
        this.recentSellingPrice = recentSellingPrice;
        this.comment = comment;
    }

    /**
     * GoodPO的构造器
     * @param name 商品名称
     * @param fatherCategoryId 商品父分类编号
     * @param type 商品型号
     * @param warningAmount 商品警戒数量
     * @param buyingPrice 商品进价
     * @param sellingPrice 商品售价
     */
    public GoodPO(String name, String fatherCategoryId, String type, Integer warningAmount, Double buyingPrice,
                  Double sellingPrice, String comment) {
        this.name = name;

        this.fatherCategoryId = fatherCategoryId;
        this.type = type;
        this.warningAmount = warningAmount;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.comment = comment;
    }

    public GoodPO() {
    }

    /*
         * 以下是getter
         */
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getFatherCategoryId(){
        return fatherCategoryId;
    }

    public String getType(){
        return type;
    }

    public Integer getWarningAmount(){
        return warningAmount;
    }

    public Double getBuyingPrice(){
        return buyingPrice;
    }

    public Double getSellingPrice(){
        return sellingPrice;
    }

    public Integer getNowAmount(){
        return nowAmount;
    }

    public Double getRecentBuyingPrice(){
        return recentBuyingPrice;
    }

    public Double getRecentSellingPrice(){
        return recentSellingPrice;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFatherCategoryId(String fatherCategoryId) {
        this.fatherCategoryId = fatherCategoryId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWarningAmount(Integer warningAmount) {
        this.warningAmount = warningAmount;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setNowAmount(Integer nowAmount) {
        this.nowAmount = nowAmount;
    }

    public void setRecentBuyingPrice(Double recentBuyingPrice) {
        this.recentBuyingPrice = recentBuyingPrice;
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
