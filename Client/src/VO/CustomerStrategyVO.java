package VO;

import Util.EmptyValue;
import Util.GiftItem;
import Util.StrategyType;
import Util.Voucher;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 继承于StrategyVO的客户回馈策略
 * 将赠品列表ArrayList的类型改为GiftItemVO
 */
public class CustomerStrategyVO extends StrategyVO {

    int level= EmptyValue.getInteger();
    ArrayList<GiftItem> gift=new ArrayList<>();//赠品列表
    Voucher vouchers=new Voucher();//代金券列表
    double discount=EmptyValue.getDouble();//折让金额

    public CustomerStrategyVO(StrategyType type, LocalDate begin, LocalDate end, int level, ArrayList<GiftItem> gift, Voucher vouchers, double discount) {
        super(type, begin, end);
        this.level = level;
        this.gift = gift;
        this.vouchers = vouchers;
        this.discount = discount;

    }

    public CustomerStrategyVO(StrategyType type, LocalDate begin, LocalDate end, String id, int level, ArrayList<GiftItem> gift, Voucher vouchers, double discount) {
        super(type, begin, end,id);
        this.level = level;
        this.gift = gift;
        this.vouchers = vouchers;
        this.discount = discount;

    }

    //get methods
    public int getLevel() {
        return level;
    }

    public ArrayList<GiftItem> getGift() {
        return gift;
    }

    public Voucher getVouchers() {
        return vouchers;
    }

    public double getDiscount() {
        return discount;
    }

    //set methods
    public void setLevel(int level) {
        this.level = level;
    }

    public void setGift(ArrayList<GiftItem> gift) {
        this.gift = gift;
    }

    public void setVouchers(Voucher vouchers) {
        this.vouchers = vouchers;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
