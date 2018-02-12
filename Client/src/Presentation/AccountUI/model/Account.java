package Presentation.AccountUI.model;

import VO.AccountVO;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {

    private final StringProperty cardNumber;
    private final StringProperty cardName;
    private final DoubleProperty balance;
    private final StringProperty remark;

    /**
     * 默认构造器
     * @param cardNumber 银行卡号
     * @param cardName 账户名称
     * @param balance 账户余额
     */
    public Account(String cardNumber, String cardName, Double balance, String remark) {
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.cardName = new SimpleStringProperty(cardName);
        this.balance = new SimpleDoubleProperty(balance);
        this.remark = new SimpleStringProperty(remark);
    }

    public Account(AccountVO vo) {
        this.cardNumber = new SimpleStringProperty(vo.getCardNumber());
        this.cardName = new SimpleStringProperty(vo.getCardName());
        this.balance = new SimpleDoubleProperty(vo.getBalance());
        this.remark = new SimpleStringProperty(vo.getNotes());
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public String getCardName() {
        return cardName.get();
    }

    public StringProperty cardNameProperty() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName.set(cardName);
    }

    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public String getRemark() {
        return remark.get();
    }

    public StringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}
