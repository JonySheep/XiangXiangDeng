package Data;

import PO.CategoryPO;
import PO.GoodPO;
import PO.GoodTreeNodePO;
import PO.GoodTreePO;
import Util.EmptyValue;
import Util.ResultMessage;
import org.junit.Test;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommodityDataImplTest {

    CommodityDataImpl commodityData = new CommodityDataImpl();

    //数据库真删除good测试完成
    @Test
    public void delGoodInDB() throws Exception {
        String goodID = "C000002-0003";
        System.out.println(commodityData.delGoodInDB(goodID));
    }

    @Test
    public void goodsearchByID() throws Exception {

    }


    //getgoodtree测试完成
    @Test
    public void getGoodTree() throws Exception {
        GoodTreePO goodTreePO = commodityData.getGoodTree();
        System.out.println(goodTreePO);
    }

    //新增目录测试成功
    @Test
    public void addCategory() throws Exception {
        CategoryPO categoryPO = new CategoryPO("","root","-1");
        CategoryPO categoryPO1 = new CategoryPO("","户外灯","C000001");
        CategoryPO categoryPO2 = new CategoryPO("","室内灯","C000001");

       System.out.println(commodityData.addCategory(categoryPO2));

    }

    //删除目录测试成功
    @Test
    public void delCategory() throws Exception {
        System.out.println(commodityData.delCategory("C000001"));
    }

    //目录名字和父节点id测试成功
    @Test
    public void updateCategory() throws Exception {
        CategoryPO categoryPO2 = new CategoryPO("C000001", EmptyValue.getString(),"empty");
        commodityData.updateCategory(categoryPO2);
    }

    //新增商品测试成功
    @Test
    public void addGood() throws Exception {
      GoodPO goodPO = new GoodPO("00-1","台灯","C000002","TD02189",20,30.0,60.0,200,30.0,70.0,"这是一款台灯");
      GoodPO goodPO1 = new GoodPO("00-1","吊灯","C000002","TD02189",20,30.0,60.0,200,30.0,70.0,"这是一款吊灯");
      GoodPO goodPO2 = new GoodPO("00-1","吸顶灯","C000002","TD02189",20,30.0,60.0,200,30.0,70.0,"这是一款吸顶灯灯");
      GoodPO goodPO3 = new GoodPO("00-1","路灯","C000003","TD02189",20,30.0,60.0,200,30.0,70.0,"这是一款台灯");
      GoodPO goodPO4 = new GoodPO("00-1","台灯","C000002","TD02189",20,30.0,60.0,200,30.0,70.0,"这是一款台灯");
      System.out.println(commodityData.addGood(goodPO3));
    }

    //商品伪删除测试成功
    @Test
    public void delGood() throws Exception {
        String id = "C000002-0001";
        System.out.println(commodityData.delGood(id));
    }

    //商品更新成功
    @Test
    public void updateGood() throws Exception {
        GoodPO goodPO = new GoodPO("C000002-0001","台灯","C000002","TD02189",30,50.0,60.0,200,30.0,70.0,"这是一款台灯");
        System.out.println(commodityData.updateGood(goodPO));
    }

    //在指定目录下模糊查找测试成功
    @Test
    public void searchGood() throws Exception {

        String input = "000";
        String categry = "C000001";
        System.out.println(commodityData.searchGood(input,categry));


    }

    @Test
    public void getCommodity() throws Exception {
        LocalDate start = LocalDate.of(2017, Month.APRIL,12);
        LocalDate end = LocalDate.of(2108,Month.JULY,22);
        System.out.println(commodityData.getCommodity(start,end));
    }

    @Test
    public void updateAmount() throws Exception {
        String id = "C000002-0002";
        LocalDate localDate = LocalDate.now();
        int amount = 300;
        double price = 50;
        assertEquals(ResultMessage.SUCCESS,commodityData.updateAmount(id,amount,price,localDate));
    }

    //更新最近售价测试成功
    @Test
    public void updateRecentSellingPrice() throws Exception {
        System.out.println(commodityData.updateRecentSellingPrice("C000002-0004",35.0));
    }

    //更新最近进价测试成功
    @Test
    public void updateRecentBuyingPrice() throws Exception {
        System.out.println(commodityData.updateRecentBuyingPrice("C000002-0004",35.0));

    }

    @Test
    public void checkExistence() throws Exception {
    }

}