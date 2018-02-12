package PO;

import Util.PackageGoodItem;
import Util.StrategyType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class PackageStrategyPO extends StrategyPO implements Serializable {

    String pkgName;//组合包名称
    double price;
    ArrayList<PackageGoodItem> pkgGood;//组合包中商品

    public PackageStrategyPO(StrategyType currentStrategy, LocalDate beginDate, LocalDate EndDate, String id, String pkgName, double price, ArrayList<PackageGoodItem> pkgGood) {
        super(currentStrategy, beginDate, EndDate,id);
        this.pkgName = pkgName;
        this.price = price;
        this.pkgGood = pkgGood;
    }

    public String getPkgName() {
        return pkgName;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<PackageGoodItem> getPkgGood() {
        return pkgGood;
    }

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
