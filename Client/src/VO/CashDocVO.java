package VO;

import PO.CashDocPO;
import PO.CashItemPO;
import Presentation.DocUI.CheckingDoc.Previewable;
import Util.DocType;

import java.util.ArrayList;

/**
 * 现金费用单的VO.
 *
 * account：银行账户
 * itemList：现金费用条目列表
 */
public class CashDocVO extends FinanceDocVO implements Previewable{

    private AccountForDocVO account;
    private ArrayList<CashItemVO> itemList;

    public CashDocVO(String prKey, UserForDocVO operator, DocType type,
                     AccountForDocVO account, ArrayList<CashItemVO> itemList) {
        this(prKey, null, operator, type, account, itemList);
    }

    public CashDocVO(String prKey, String id, UserForDocVO operator,
                     DocType type, AccountForDocVO account,
                     ArrayList<CashItemVO> itemList) {
        super(prKey, id, operator, type);
        this.account = account;
        this.itemList = itemList;
    }

    public AccountForDocVO getAccount() {
        return account;
    }

    public ArrayList<CashItemVO> getItemList() {
        return itemList;
    }

    public double getTotal() {
        double ret = 0.0;
        for (CashItemVO item : itemList)
            ret += item.getAmount();
        return ret;
    }

    @Override
    public CashDocPO toPO() {
        ArrayList<CashItemPO> itemPOList = new ArrayList<>();
        for (CashItemVO itemVO : itemList)
            itemPOList.add(itemVO.toPO());
        return new CashDocPO(getPrKey(), getId(), getOperator().getId(), getType(),
                account.getCardNumber(), itemPOList);
    }

    @Override
    public DocType getDocType() {
        return getType();
    }

    @Override
    public String getName() {
        return getOperator().getName();
    }

    @Override
    public String getComment() {
        return "";
    }
}
