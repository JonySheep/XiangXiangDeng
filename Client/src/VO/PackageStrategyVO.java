package VO;

import PO.GoodPO;
import Util.EmptyValue;
import Util.PackageGoodItem;
import Util.StrategyType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class PackageStrategyVO extends StrategyVO {

    String pkgName= EmptyValue.getString();//组合包名称
    double price=EmptyValue.getDouble();
    ArrayList<PackageGoodItem> pkgGood;//组合包中商品

    /**
     * 组合降价策略的构造方法，无ID，用来新增策略
     * @param type
     * @param begin
     * @param end
     * @param pkgName
     * @param price
     * @param pkgGood
     */
    public PackageStrategyVO(StrategyType type, LocalDate begin, LocalDate end,String pkgName, double price, ArrayList<PackageGoodItem> pkgGood) {
        super(type, begin, end);
        this.pkgName = pkgName;
        this.price = price;
        this.pkgGood = pkgGood;
    }


    /**
     * 组合降价策略的构造方法，用来进行与PO的转换
     * @param type
     * @param begin
     * @param end
     * @param id
     * @param pkgName
     * @param price
     * @param pkgGood
     */
    public PackageStrategyVO(StrategyType type, LocalDate begin, LocalDate end, String id,String pkgName, double price, ArrayList<PackageGoodItem> pkgGood) {
        super(type, begin, end,id);
        this.pkgName = pkgName;
        this.price = price;
        this.pkgGood = pkgGood;
    }

    //get methods

    public String getPkgName() {
        return pkgName;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<PackageGoodItem> getPkgGood() {
        return pkgGood;
    }

    //set methods

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPkgGood(ArrayList<PackageGoodItem> pkgGood) {
        this.pkgGood = pkgGood;
    }
}
