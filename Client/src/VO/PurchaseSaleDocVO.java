package VO;

import Util.DocType;

import java.util.ArrayList;

/**
 * 进货类、销售类单据VO的父类.
 *
 * customer：供应商/销售商
 * itemList：商品列表
 * comment：备注
 */
public abstract class PurchaseSaleDocVO extends DocVO {

    private CustomerForDocVO customer;
    private ArrayList<GoodItemForPurchaseSaleDocVO> itemList;
    private String comment;

    public PurchaseSaleDocVO(String prKey, String id, UserForDocVO operator,
                             DocType type, CustomerForDocVO customer,
                             ArrayList<GoodItemForPurchaseSaleDocVO> itemList,
                             String comment) {
        super(prKey, id, operator, type);
        this.customer = customer;
        this.itemList = itemList;
        this.comment = comment;
    }

    public CustomerForDocVO getCustomer() {
        return customer;
    }

    public ArrayList<GoodItemForPurchaseSaleDocVO> getItemList() {
        return itemList;
    }

    public String getComment() {
        return comment;
    }

    public double getTotal() {
        double total = 0.0;
        for (GoodItemForPurchaseSaleDocVO item: itemList)
            total += item.getTotalPrice();
        return total;
    }
}
