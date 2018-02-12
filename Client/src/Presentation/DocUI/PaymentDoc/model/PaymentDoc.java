package Presentation.DocUI.PaymentDoc.model;

import Util.DocType;
import Util.EmptyValue;
import VO.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class PaymentDoc  {

    private final StringProperty ID;
    private final StringProperty PrKey;
    private UserForDocVO currentOperator;
    private DocType Type;
    private CustomerForDocVO currentCustomer;
    private ArrayList<AccountForDocVO> Accounts=new ArrayList<>();

    //For CashDoc
    private AccountForDocVO Account;
    private ArrayList<CashItemVO> CashItems=new ArrayList<>();

    /**
     * 默认构造函数
     */
    public PaymentDoc(){
        ID=new SimpleStringProperty(EmptyValue.getString());
        PrKey=new SimpleStringProperty(EmptyValue.getString());
        currentOperator=null;
        Type=DocType.PAYING;
        currentCustomer=null;
        Account=null;
    }

    /**
     * 新增过程使用的带有类型的空对象
     * @param type 单据类型
     */
    public PaymentDoc(DocType type) {
        ID=new SimpleStringProperty("");
        PrKey=new SimpleStringProperty("");
        currentOperator=null;
        Type=type;
        currentCustomer=null;
        Account=null;
    }

    /**
     * 制定收/付款单 （正式单据 有ID
     * @param prKey 唯一标识符
     * @param id 编号
     * @param operator 操作员
     * @param type 类型
     * @param customer 客户
     * @param accountList 银行账户
     */
    public PaymentDoc(String prKey, String id, UserForDocVO operator,
                      DocType type, CustomerForDocVO customer,
                      ArrayList<AccountForDocVO> accountList){
    ID=new SimpleStringProperty(id);
    PrKey=new SimpleStringProperty(prKey);
    currentOperator=operator;
    currentCustomer=customer;
    Type=type;
    Accounts=accountList;
    }


    /**
     * 制定收/付款单 （草稿、无ID
     * @param prKey 唯一标识符
     * @param operator 操作员
     * @param type 类型
     * @param customer 客户
     * @param accountList 银行账户
     */
    public PaymentDoc(String prKey, UserForDocVO operator,
                      DocType type, CustomerForDocVO customer,
                      ArrayList<AccountForDocVO> accountList){
        ID=new SimpleStringProperty(EmptyValue.getString());
        PrKey=new SimpleStringProperty(prKey);
        currentOperator=operator;
        currentCustomer=customer;
        Type=type;
        Accounts=accountList;
    }


    /**
     * 制定现金费用单  （草稿、无ID
     * @param prKey 唯一标识符
     * @param operator 操作员
     * @param type 类型
     * @param account 选定银行账户
     * @param cashItems 现金条目
     */
    public PaymentDoc(String prKey,UserForDocVO operator,
                      DocType type,AccountForDocVO account,
                      ArrayList<CashItemVO> cashItems){
        ID=new SimpleStringProperty(EmptyValue.getString());
        PrKey=new SimpleStringProperty(prKey);
        currentOperator=operator;
        Type=type;
        Account=account;
        CashItems=cashItems;
    }


    /**
     * 制定现金费用单  （正式单据，有ID
     * @param id 单据ID
     * @param prKey 唯一标识符
     * @param operator 操作员
     * @param type 类型
     * @param account 选定银行账户
     * @param cashItems 现金条目
     */
    public PaymentDoc(String id,String prKey,UserForDocVO operator,
                      DocType type,AccountForDocVO account,
                      ArrayList<CashItemVO> cashItems){
        ID=new SimpleStringProperty(id);
        PrKey=new SimpleStringProperty(prKey);
        currentOperator=operator;
        Type=type;
        Account=account;
        CashItems=cashItems;
    }


    //Getter
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

    public CustomerForDocVO getCurrentCustomer() {
        return currentCustomer;
    }

    public ArrayList<AccountForDocVO> getAcounts() {
        return Accounts;
    }

    public AccountForDocVO getAccount() {
        return Account;
    }

    public ArrayList<CashItemVO> getCashItems() {
        return CashItems;
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

    public void setCurrentCustomer(CustomerForDocVO currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void setAcounts(ArrayList<AccountForDocVO> acounts) {
        Accounts = acounts;
    }

    public void setAccount(AccountForDocVO account) {
        Account = account;
    }

    public void setCashItems(ArrayList<CashItemVO> cashItems) {
        CashItems = cashItems;
    }
}
