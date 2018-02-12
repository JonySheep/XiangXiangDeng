package VO;

import PO.GiftItemPO;
import PO.GoodItemForPurchaseSaleDocPO;
import PO.SaleDocPO;
import Presentation.DocUI.CheckingDoc.Previewable;
import Util.DocType;
import Util.GiftItem;

import java.util.ArrayList;

/**
 * 销售类单据的VO.
 *
 * salesman：业务员
 * giftList：赠品列表
 * rebate：折让
 * voucher：代金券金额
 */
public class SaleDocVO extends PurchaseSaleDocVO implements Previewable{

    private UserForDocVO salesman;
    private ArrayList<GiftItem> giftList;
    private double rebate;
    private int voucher;

    public SaleDocVO(String prKey, UserForDocVO operator,
                     DocType type, CustomerForDocVO customer,
                     ArrayList<GoodItemForPurchaseSaleDocVO> itemList,
                     UserForDocVO salesman, String comment) {
        this(prKey, null, operator, type, customer, itemList, salesman, comment);
    }

    public SaleDocVO(String prKey, String id, UserForDocVO operator,
                     DocType type, CustomerForDocVO customer,
                     ArrayList<GoodItemForPurchaseSaleDocVO> itemList,
                     UserForDocVO salesman, String comment) {
        this(prKey, id, operator, type, customer, itemList, salesman,
                null, 0.0, 0, comment);
    }

    public SaleDocVO(String prKey, UserForDocVO operator,
                     DocType type, CustomerForDocVO customer,
                     ArrayList<GoodItemForPurchaseSaleDocVO> itemList,
                     UserForDocVO salesman, ArrayList<GiftItem> giftList,
                     double rebate, int voucher, String comment) {
        this(prKey, null, operator, type, customer, itemList, salesman,
                giftList, rebate, voucher, comment);
    }

    public SaleDocVO(String prKey, String id, UserForDocVO operator,
                     DocType type, CustomerForDocVO customer,
                     ArrayList<GoodItemForPurchaseSaleDocVO> itemList,
                     UserForDocVO salesman, ArrayList<GiftItem> giftList,
                     double rebate, int voucher, String comment) {
        super(prKey, id, operator, type, customer, itemList, comment);
        this.salesman = salesman;
        this.giftList = giftList;
        this.rebate = rebate;
        this.voucher = voucher;
    }

    public UserForDocVO getSalesman() {
        return salesman;
    }

    public ArrayList<GiftItem> getGiftList() {
        return giftList;
    }

    public double getRebate() {
        return rebate;
    }

    public int getVoucher() {
        return voucher;
    }

    public SaleDocPO toPO() {
        ArrayList<GoodItemForPurchaseSaleDocPO> itemPOList = new ArrayList<>();
        for (GoodItemForPurchaseSaleDocVO item : getItemList())
            itemPOList.add(item.toPO());
        ArrayList<GiftItemPO> giftPOList = new ArrayList<>();
        for (GiftItem gift : giftList)
            giftPOList.add(gift.toPO());
        return new SaleDocPO(getPrKey(), getId(), getOperator().getId(),
                getType(), getCustomer().getId(), itemPOList, salesman.getId(),
                giftPOList, rebate, voucher, getComment());
    }

    @Override
    public DocType getDocType() {
        return getType();
    }

    @Override
    public String getName() {
        return getCustomer().getName();
    }
}
