package VO;

import PO.FinanceDocPO;
import Presentation.DocUI.CheckingDoc.Previewable;
import Util.DocType;

/**
 * 财务类单据VO的父类.
 */
public abstract class FinanceDocVO extends DocVO implements Previewable{

    public FinanceDocVO(String prKey, String id, UserForDocVO operator, DocType type) {
        super(prKey, id, operator, type);
    }

    public abstract FinanceDocPO toPO();

    @Override
    public String getComment() {
        return "";
    }
}
