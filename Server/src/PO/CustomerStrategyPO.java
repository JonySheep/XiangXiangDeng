package PO;

import Util.GiftItem;
import Util.StrategyType;
import Util.Voucher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerStrategyPO extends StrategyPO implements Serializable {

    int level;
    ArrayList<GiftItem> gift;//赠品列表
    Voucher vouchers;//代金券列表
    double discount;//折让金额

    public CustomerStrategyPO(StrategyType currentStrategy, LocalDate beginDate, LocalDate EndDate, String id, int level, ArrayList<GiftItem> gift, Voucher vouchers, double discount) {
        super(currentStrategy, beginDate, EndDate,id);
        this.level = level;
        this.gift = gift;
        this.vouchers = vouchers;
        this.discount = discount;
    }

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
}
