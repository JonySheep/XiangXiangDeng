package Util;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Voucher implements Serializable{

    int amount;

    public Voucher(){
        this.amount=0;
    }

    public Voucher(int amount) {
        this.amount = amount;
    }

    //get methods

    public int getAmount() {
        return amount;
    }

    //set method

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
