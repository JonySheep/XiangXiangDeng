package Presentation.DocUI.GoodDoc.model;

import Util.DocType;
import Util.EmptyValue;
import VO.GoodItemForGoodDocVO;
import VO.UserForDocVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class  GoodDoc {

    private final StringProperty ID;
    private final StringProperty PrKey;
    private UserForDocVO currentOperator;
    private DocType Type;
    private ArrayList<GoodItemForGoodDocVO> Goods;
    private final StringProperty Comment;


    /**
     * 默认构造
     */
    public GoodDoc(){
        ID=new SimpleStringProperty(EmptyValue.getString());
        PrKey=new SimpleStringProperty(EmptyValue.getString());
        currentOperator=null;
        Type=DocType.GOOD_OVERFLOW;
        Goods=null;
        Comment=new SimpleStringProperty(EmptyValue.getString());
    }


    /**
     * 新增时使用的构造方法
     * @param docType 单据类型
     */
    public GoodDoc(DocType docType){
        ID=new SimpleStringProperty(EmptyValue.getString());
        PrKey=new SimpleStringProperty("");
        currentOperator=null;
        Type=docType;
        Goods=null;
        Comment=new SimpleStringProperty("");
    }

    /**
     * 库存报溢、报损单（草稿 无ID
     * @param prKey 唯一标识符
     * @param operator 操作员
     * @param type 类型
     * @param itemList 商品列表
     * @param comment 备注
     */
    public GoodDoc(String prKey, UserForDocVO operator, DocType type,
                   ArrayList<GoodItemForGoodDocVO> itemList, String comment) {

        ID=new SimpleStringProperty(EmptyValue.getString());
        PrKey=new SimpleStringProperty(prKey);
        currentOperator=operator;
        Type=type;
        Goods=itemList;
        Comment=new SimpleStringProperty(comment);
    }


    /**
     * 库存报溢、报损单（审批通过后的正式版本
     * @param id 单据id
     * @param prKey 唯一标识符
     * @param operator 操作员
     * @param type 类型
     * @param itemList 商品列表
     * @param comment 备注
     */
    public GoodDoc(String id,String prKey, UserForDocVO operator, DocType type,
                   ArrayList<GoodItemForGoodDocVO> itemList, String comment) {

        ID=new SimpleStringProperty(id);
        PrKey=new SimpleStringProperty(prKey);
        currentOperator=operator;
        Type=type;
        Goods=itemList;
        Comment=new SimpleStringProperty(comment);
    }


    public String getID() {
        return ID.get();
    }

    public String getPrKey() {
        return PrKey.get();
    }

    public UserForDocVO getCurrentOperator() {
        return currentOperator;
    }

    public DocType getType() {
        return Type;
    }

    public ArrayList<GoodItemForGoodDocVO> getGoods() {
        return Goods;
    }

    public String getComment() {
        return Comment.get();
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public void setPrKey(String prKey) {
        this.PrKey.set(prKey);
    }

    public void setCurrentOperator(UserForDocVO currentOperator) {
        this.currentOperator = currentOperator;
    }

    public void setType(DocType type) {
        Type = type;
    }

    public void setGoods(ArrayList<GoodItemForGoodDocVO> goods) {
        Goods = goods;
    }

    public void setComment(String comment) {
        this.Comment.set(comment);
    }
}
