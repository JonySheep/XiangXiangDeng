package VO;

import PO.GoodDocPO;
import PO.GoodItemForGoodDocPO;
import Presentation.DocUI.CheckingDoc.Previewable;
import Util.DocType;

import java.util.ArrayList;

/**
 * 库存类单据VO.
 *
 * itemList：商品列表
 * comment：备注
 */
public class GoodDocVO extends DocVO implements Previewable{

    private ArrayList<GoodItemForGoodDocVO> itemList;
    private String comment;

    public GoodDocVO(String prKey, UserForDocVO operator, DocType type,
                     ArrayList<GoodItemForGoodDocVO> itemList, String comment) {
        this(prKey, null, operator, type, itemList, comment);
    }

    public GoodDocVO(String prKey, String id, UserForDocVO operator, DocType type,
                     ArrayList<GoodItemForGoodDocVO> itemList, String comment) {
        super(prKey, id, operator, type);
        this.itemList = itemList;
        this.comment = comment;
    }

    public ArrayList<GoodItemForGoodDocVO> getItemList() {
        return itemList;
    }

    public String getComment() {
        return comment;
    }

    public GoodDocPO toPO() {
        ArrayList<GoodItemForGoodDocPO> itemPOList = new ArrayList<>();
        for (GoodItemForGoodDocVO itemVO : itemList)
            itemPOList.add(itemVO.toPO());
        return new GoodDocPO(getPrKey(), getId(), getOperator().getId(),
                getType(), itemPOList, comment);
    }

    @Override
    public DocType getDocType() {
        return getType();
    }

    @Override
    public String getName() {
        return getOperator().getName();
    }
}
