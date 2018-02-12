package PO;

import java.io.Serializable;

/**
 * Created by Jeven on 2017/10/22.
 */
public class AccountPO  implements Serializable {
    String cardNumber;
    String cardName;
    double balance;
    String notes;


    /**
     *
     * @param number 银行卡号
     * @param name 账户名称
     * @param balance 银行卡余额
     * @param notes 备注
     */
    public AccountPO(String number, String name, double balance,String notes) {
        cardNumber = number;
        cardName = name;
        this.balance = balance;
        this.notes = notes;
    }

    public AccountPO(){}
    public String getCardNumber() {
        return cardNumber;
    }
    public String getCardName() {
        return cardName;
    }
    public double getBalance() {
        return balance;
    }
    public String getNotes(){
        return notes;
    }
    public void setCardNumber(String number) {
        this.cardNumber = number;
    }
    public void setCardName(String name){
        this.cardName = name;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public void setNotes(String notes){
        this.notes = notes;

    }
}
