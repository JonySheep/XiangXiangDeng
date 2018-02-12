package PO;

import Util.GiftItem;
import Util.StrategyType;
import Util.Voucher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class AmountStrategyPO extends StrategyPO implements Serializable {

    double amount;//总额额度
    ArrayList<GiftItem> gift;//赠品列表
    Voucher vouchers;//代金券列表


    public AmountStrategyPO(StrategyType currentStrategy, LocalDate beginDate, LocalDate EndDate, String id, double amount, ArrayList<GiftItem> gift, Voucher vouchers) {
        super(currentStrategy, beginDate, EndDate,id);
        this.amount = amount;
        this.gift = gift;
        this.vouchers = vouchers;
    }

    //getter
    public double getAmount() {
        return amount;
    }

    public ArrayList<GiftItem> getGift() {
        return gift;
    }

    public Voucher getVouchers() {
        return vouchers;
    }

    //setter
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setGift(ArrayList<GiftItem> gift) {
        this.gift = gift;
    }

    public void setVouchers(Voucher vouchers) {
        this.vouchers = vouchers;
    }
}
