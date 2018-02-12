package Data;

import PO.AmountStrategyPO;
import PO.CustomerStrategyPO;
import PO.PackageStrategyPO;
import Util.GiftItem;
import Util.PackageGoodItem;
import Util.StrategyType;
import Util.Voucher;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StrategyDataImplTest {

    StrategyDataImpl strategyData = new StrategyDataImpl();

    //新增客户回馈策略测试完成
    @Test
    public void insertCusStrategy() throws Exception {
        int level = 5;
        GiftItem giftItem1 = new GiftItem("台灯a","C000002-0001",30);
        GiftItem giftItem2 = new GiftItem("吊灯","C000002-0002",20);
        ArrayList<GiftItem> giftItems = new ArrayList<>();
        giftItems.add(giftItem1);
        giftItems.add(giftItem2);
        Voucher voucher = new Voucher(50);
        double discount = 20;
        LocalDate start = LocalDate.of(2108, Month.JANUARY,01);
        LocalDate end = LocalDate.of(2108, Month.JANUARY,30);
        CustomerStrategyPO customerStrategyPO = new CustomerStrategyPO(StrategyType.CUSTOMER,start,end,"",level,giftItems,voucher,discount);

        System.out.println(strategyData.insertCusStrategy(customerStrategyPO));
    }

    //新增组合降价策略测试完成
    @Test
    public void insertPkgStrategy() throws Exception {
        String name = "随便一个组合包";
        double price = 233;
        PackageGoodItem packageGoodItem = new PackageGoodItem("C000002-0001",30);
        PackageGoodItem packageGoodItem1= new PackageGoodItem("C000002-0002",30);
        ArrayList<PackageGoodItem> packageGoodItems = new ArrayList<>();
        packageGoodItems.add(packageGoodItem);
        packageGoodItems.add(packageGoodItem1);
        LocalDate start = LocalDate.of(2108, Month.JANUARY,01);
        LocalDate end = LocalDate.of(2108, Month.JANUARY,30);
        PackageStrategyPO packageStrategyPO = new PackageStrategyPO(StrategyType.PACKAGE,start,end,"",name,price,packageGoodItems);
        System.out.println(strategyData.insertPkgStrategy(packageStrategyPO));



    }

    //新增总价降价策略降价完成
    @Test
    public void insertAmouStrategy() throws Exception {
        double amount = 300;
        GiftItem giftItem1 = new GiftItem("台灯a","C000002-0001",30);
        GiftItem giftItem2 = new GiftItem("吊灯","C000002-0002",20);
        ArrayList<GiftItem> giftItems = new ArrayList<>();
        giftItems.add(giftItem1);
        giftItems.add(giftItem2);
        Voucher voucher = new Voucher(50);
        LocalDate start = LocalDate.of(2108, Month.JANUARY,01);
        LocalDate end = LocalDate.of(2108, Month.JANUARY,30);

        AmountStrategyPO amountStrategyPO = new AmountStrategyPO(StrategyType.AMMOUNT,start,end,"",amount,giftItems,voucher);
        System.out.println(strategyData.insertAmouStrategy(amountStrategyPO));

    }

    //删除测试完成
    @Test
    public void delete() throws Exception {
        double amount = 300;
        GiftItem giftItem1 = new GiftItem("台灯a","C000002-0001",30);
        GiftItem giftItem2 = new GiftItem("吊灯","C000002-0002",20);
        ArrayList<GiftItem> giftItems = new ArrayList<>();
        giftItems.add(giftItem1);
        giftItems.add(giftItem2);
        Voucher voucher = new Voucher(50);
        LocalDate start = LocalDate.of(2108, Month.JANUARY,01);
        LocalDate end = LocalDate.of(2108, Month.JANUARY,30);

        AmountStrategyPO amountStrategyPO = new AmountStrategyPO(StrategyType.AMMOUNT,start,end,"AS000001",amount,giftItems,voucher);
        System.out.println(strategyData.delete(amountStrategyPO));
    }

    @Test
    public void getStrategy() throws Exception {
    }

    //获得用户策略
    @Test
    public void getCurrentCusStrategy() throws Exception {
        int level = 5;
        LocalDate date = LocalDate.of(2108,Month.JANUARY,20);
        System.out.println(strategyData.getCurrentCusStrategy(date,level).getId());
    }

    //获得组合策略
    @Test
    public void getCurrentPkgStrategy() throws Exception {
        LocalDate date = LocalDate.of(2108,Month.JANUARY,20);
        System.out.println(strategyData.getCurrentPkgStrategy(date));

    }

    //获得总价策略
    @Test
    public void getCurrentAmouStrategy() throws Exception {
        LocalDate date = LocalDate.of(2108,Month.JANUARY,20);
        double price = 345;
        System.out.println(strategyData.getCurrentAmouStrategy(date,price).getId());

    }

}