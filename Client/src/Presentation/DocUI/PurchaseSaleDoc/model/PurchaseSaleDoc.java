package Presentation.DocUI.PurchaseSaleDoc.model;

import Util.DocType;
import Util.EmptyValue;
import Util.GiftItem;
import VO.CustomerForDocVO;
import VO.GoodItemForGoodDocVO;
import VO.GoodItemForPurchaseSaleDocVO;
import VO.UserForDocVO;
import javafx.beans.property.*;

import java.util.ArrayList;

/**
 * 用来在界面上显示进货单、进货退货单、销售单、销售退货单的模型类
 */
public class PurchaseSaleDoc {

    private final StringProperty DocPrKey;
    private final StringProperty DocID;
    private DocType type;
    private CustomerForDocVO Cus;
    private UserForDocVO User;
    private ArrayList<GoodItemForPurchaseSaleDocVO> GoodList=new ArrayList<>();
    private final DoubleProperty Amount;
    private final StringProperty Comment;

    //销售单属性
    private final DoubleProperty Discount;
    private final IntegerProperty Voucher;
    private ArrayList<GiftItem> Gifts=new ArrayList<>();


    public PurchaseSaleDoc(DocType Type){
        DocPrKey=new SimpleStringProperty("");
        DocID=new SimpleStringProperty("");
        type=Type;
        Cus=null;
        User=null;
        Amount=new SimpleDoubleProperty(0.0);
        Comment=new SimpleStringProperty("");

        Discount=new SimpleDoubleProperty(0.0);
        Voucher=new SimpleIntegerProperty(0);
    }


    public PurchaseSaleDoc(){
        DocPrKey=new SimpleStringProperty(EmptyValue.getString());
        DocID=new SimpleStringProperty(EmptyValue.getString());
        Cus=null;
        User=null;
        Amount=new SimpleDoubleProperty(EmptyValue.getDouble());
        Comment=new SimpleStringProperty(EmptyValue.getString());

        Discount=new SimpleDoubleProperty(EmptyValue.getDouble());
        Voucher=new SimpleIntegerProperty(EmptyValue.getInteger());
    }

    /**
     * 进货、进货退货、销售退货单的属性 （非草稿
     * @param prkey 唯一标识符
     * @param docType 单据类型
     * @param cus 客户
     * @param clerk 操作员
     * @param goodList 商品列表
     * @param amount 总额
     * @param comment 备注
     */
    public PurchaseSaleDoc(String prkey,DocType docType, CustomerForDocVO cus, UserForDocVO clerk, ArrayList<GoodItemForPurchaseSaleDocVO> goodList, Double amount,
                           String comment) {
        DocPrKey=new SimpleStringProperty(prkey);
        DocID = new SimpleStringProperty(EmptyValue.getString());
        type=docType;
        Cus=cus;
        User=clerk;
        GoodList = goodList;
        Amount = new SimpleDoubleProperty(amount);
        Comment = new SimpleStringProperty(comment);

        //初始化为空
        Discount=new SimpleDoubleProperty(EmptyValue.getDouble());
        Voucher=new SimpleIntegerProperty(EmptyValue.getInteger());
    }

    /**
     * 进货、进货退货、销售退货单的属性 （非草稿
     * @param docID 单据ID （审批通过后才会生成
     * @param prkey 唯一标识符
     * @param docType 单据类型
     * @param cus 客户
     * @param clerk 操作员
     * @param goodList 商品列表
     * @param amount 总额
     * @param comment 备注
     */
    public PurchaseSaleDoc(String docID,String prkey,DocType docType, CustomerForDocVO cus, UserForDocVO clerk, ArrayList<GoodItemForPurchaseSaleDocVO> goodList, Double amount,
                           String comment) {
        DocPrKey=new SimpleStringProperty(prkey);
        DocID = new SimpleStringProperty(docID);
        type=docType;
        Cus=cus;
        User=clerk;
        GoodList = goodList;
        Amount = new SimpleDoubleProperty(amount);
        Comment = new SimpleStringProperty(comment);

        //初始化为空
        Discount=new SimpleDoubleProperty(EmptyValue.getDouble());
        Voucher=new SimpleIntegerProperty(EmptyValue.getInteger());
    }


    /**
     * 销售单的属性 (草稿箱用，没有ID
     * @param prkey 唯一标识符
     * @param docType 单据类型
     * @param cus 客户
     * @param clerk 操作员
     * @param goodList 商品列表
     * @param amount 总额
     * @param comment 备注
     * @param discount 折让
     * @param voucher 代金券金额
     * @param gifts 赠品列表
     */
    public PurchaseSaleDoc(String prkey,DocType docType, CustomerForDocVO cus, UserForDocVO clerk, ArrayList<GoodItemForPurchaseSaleDocVO> goodList, Double amount,
                           String comment, Double discount, int voucher, ArrayList<GiftItem> gifts) {

        DocPrKey=new SimpleStringProperty(prkey);
        DocID = new SimpleStringProperty(EmptyValue.getString());
        type=docType;
        Cus=cus;
        User=clerk;
        GoodList = goodList;
        Amount = new SimpleDoubleProperty(amount);
        Comment = new SimpleStringProperty(comment);
        Discount = new SimpleDoubleProperty(discount);
        Voucher = new SimpleIntegerProperty(voucher);
        Gifts = gifts;
    }

    /**
     * 销售单的属性
     * @param docID 单据ID （审批通过后才会生成
     * @param prkey 唯一标识符
     * @param docType 单据类型
     * @param cus 客户
     * @param clerk 操作员
     * @param goodList 商品列表
     * @param amount 总额
     * @param comment 备注
     * @param discount 折让
     * @param voucher 代金券金额
     * @param gifts 赠品列表
     */
    public PurchaseSaleDoc(String docID,String prkey,DocType docType, CustomerForDocVO cus, UserForDocVO clerk, ArrayList<GoodItemForPurchaseSaleDocVO> goodList, Double amount,
                           String comment, Double discount, int voucher, ArrayList<GiftItem> gifts) {

        DocPrKey=new SimpleStringProperty(prkey);
        DocID = new SimpleStringProperty(docID);
        type=docType;
        Cus=cus;
        User=clerk;
        GoodList = goodList;
        Amount = new SimpleDoubleProperty(amount);
        Comment = new SimpleStringProperty(comment);
        Discount = new SimpleDoubleProperty(discount);
        Voucher = new SimpleIntegerProperty(voucher);
        Gifts = gifts;
    }

    public String getDocID() {
        return DocID.get();
    }

    public CustomerForDocVO getCus() {
        return Cus;
    }

    public UserForDocVO getClerk() {
        return User;
    }


    public ArrayList<GoodItemForPurchaseSaleDocVO> getGoodList() {
        return GoodList;
    }

    public double getAmount() {
        return Amount.get();
    }


    public String getComment() {
        return Comment.get();
    }

    public double getDiscount() {
        return Discount.get();
    }

    public int getVoucher() {
        return Voucher.get();
    }

    public ArrayList<GiftItem> getGifts() {
        return Gifts;
    }


    public void setDocID(String docID) {
        this.DocID.set(docID);
    }

    public void setCus(CustomerForDocVO cus) {
        Cus = cus;
    }

    public void setClerk(UserForDocVO clerk) {
        this.User=clerk;
    }

    public void setGoodList(ArrayList<GoodItemForPurchaseSaleDocVO> goodList) {
        GoodList = goodList;
    }

    public void setAmount(double amount) {
        this.Amount.set(amount);
    }

    public void setComment(String comment) {
        this.Comment.set(comment);
    }

    public void setDiscount(double discount) {
        this.Discount.set(discount);
    }

    public void setVoucher(int voucher) {
        this.Voucher.set(voucher);
    }


    public void setGifts(ArrayList<GiftItem> gifts) {
        Gifts = gifts;
    }

    public String getDocPrKey() {
        return DocPrKey.get();
    }
    public DocType getType() {
        return type;
    }

    public void setDocPrKey(String docPrKey) {
        this.DocPrKey.set(docPrKey);
    }

    public void setType(DocType type) {
        this.type = type;
    }
}
