package VO;

import Util.EmptyValue;
import Util.GiftItem;
import Util.StrategyType;
import Util.Voucher;

import java.time.LocalDate;
import java.util.ArrayList;

public class AmountStrategyVO extends  StrategyVO {

    double amount= EmptyValue.getDouble();//总额额度
    ArrayList<GiftItem> gift=new ArrayList<>();//赠品列表
    Voucher vouchers=new Voucher();//代金券列表

    public AmountStrategyVO(StrategyType type, LocalDate begin, LocalDate end, double amount, ArrayList<GiftItem> gift, Voucher vouchers) {
        super(type, begin, end);
        this.amount = amount;
        this.gift = gift;
        this.vouchers = vouchers;
    }

    public AmountStrategyVO(StrategyType type, LocalDate begin, LocalDate end, String id, double amount, ArrayList<GiftItem> gift, Voucher vouchers) {
        super(type, begin, end,id);
        this.amount = amount;
        this.gift = gift;
        this.vouchers = vouchers;
    }

    //get methods

    public double getAmount() {
        return amount;
    }

    public ArrayList<GiftItem> getGift() {
        return gift;
    }

    public Voucher getVouchers() {
        return vouchers;
    }

    //set methods

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
