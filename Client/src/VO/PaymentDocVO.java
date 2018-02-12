package VO;

import PO.AccountForDocPO;
import PO.PaymentDocPO;
import Presentation.DocUI.CheckingDoc.Previewable;
import Util.DocType;

import java.util.ArrayList;

/**
 * 收款/付款单VO.
 *
 * customer：供应商/销售商
 * accountList：转账银行账户列表
 */
public class PaymentDocVO extends FinanceDocVO implements Previewable{

    private CustomerForDocVO customer;
    private ArrayList<AccountForDocVO> accountList;

    public PaymentDocVO(String prKey, UserForDocVO operator, DocType type,
                        CustomerForDocVO customer, ArrayList<AccountForDocVO> accountList) {
        this(prKey, null, operator, type, customer, accountList);
    }

    public PaymentDocVO(String prKey, String id, UserForDocVO operator,
                        DocType type, CustomerForDocVO customer,
                        ArrayList<AccountForDocVO> accountList) {
        super(prKey, id, operator, type);
        this.customer = customer;
        this.accountList = accountList;
    }

    public CustomerForDocVO getCustomer() {
        return customer;
    }

    public ArrayList<AccountForDocVO> getAccountList() {
        return accountList;
    }

    public void setCustomer(CustomerForDocVO customer) {
        this.customer = customer;
    }

    public double getTotal() {
        double total = 0.0;
        for (AccountForDocVO account: accountList) {
            total += account.getAmount();
        }
        return total;
    }

    @Override
    public PaymentDocPO toPO() {
        ArrayList<AccountForDocPO> accountPOList = new ArrayList<>();
        for (AccountForDocVO account : accountList)
            accountPOList.add(account.toPO());
        return new PaymentDocPO(getPrKey(), getId(), getOperator().getId(),
                getType(), customer.getId(), accountPOList);
    }

    @Override
    public DocType getDocType() {
        return getType();
    }

    @Override
    public String getName() {
        return customer.getName();
    }

    @Override
    public String getComment() {
        return "";
    }
}
