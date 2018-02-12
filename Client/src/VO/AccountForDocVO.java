package VO;

import PO.AccountForDocPO;

/**
 * 收款/付款单中的账户VO.
 */
public class AccountForDocVO {

    private String cardNumber;
    private String cardName;
    private double amount;
    private String comment;

    public AccountForDocVO(String cardNumber, String cardName) {
        this(cardNumber, cardName, 0.0, "");
    }

    public AccountForDocVO(String cardNumber, String cardName, double amount, String comment) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.amount = amount;
        this.comment = comment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AccountForDocPO toPO() {
        return new AccountForDocPO(cardNumber, amount, comment);
    }
}