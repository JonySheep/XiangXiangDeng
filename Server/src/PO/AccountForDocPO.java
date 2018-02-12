package PO;

import java.io.Serializable;

public class AccountForDocPO implements Serializable{

    private String cardNumber;
    private double amount;
    private String comment;

    public AccountForDocPO(String cardNumber, double amount, String comment) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.comment = comment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }
}
