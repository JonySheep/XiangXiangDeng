package Presentation.StrategyUI;

import BusinessLogic.StrategyBL.StrategyController;
import BusinessLogicService.StrategyBLService;
import Util.*;
import VO.AmountStrategyVO;
import VO.CustomerStrategyVO;
import Util.GiftItem;
import VO.PackageStrategyVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class StrategyDataHelper {

    private static StrategyDataHelper ourInstance = new StrategyDataHelper();

    public static StrategyDataHelper getInstance() {
        return ourInstance;
    }

    private ObservableList<CustomerStrategyVO> CusStrategyList = FXCollections.observableArrayList();

    private ObservableList<AmountStrategyVO> AmouStrategyList=FXCollections.observableArrayList();

    private StrategyBLService blService = new StrategyController();

    private DataChecker checker=new DataChecker();

    /*
    测试用数据
     */
    //CustomerStrategy
    LocalDate begin = LocalDate.of(2017, 10, 30);
    LocalDate end = LocalDate.of(2017, 11, 30);
    GiftItem gift1 = new GiftItem("超级香香香灯", "XXD-23333", 5);
    GiftItem gift2 = new GiftItem("臭臭灯", "CCD-22222", 3);
    Voucher voucher1 = new Voucher(100);
    Voucher voucher2 = new Voucher(20);
    ArrayList<GiftItem> gifts = new ArrayList<>();

    public StrategyDataHelper() {
        //CustomerStrategy
        gifts.add(gift1);
        gifts.add(gift2);
        CusStrategyList.add(new CustomerStrategyVO(StrategyType.CUSTOMER, begin, end, 5, gifts, voucher1, 20));
        CusStrategyList.add(new CustomerStrategyVO(StrategyType.CUSTOMER, begin, end, 4, gifts, voucher2, 0));

        AmouStrategyList.add(new AmountStrategyVO(StrategyType.AMOUNT,begin,end,500.0,gifts,voucher1));
    }


    /**
     * 公共的得到客户回馈策略列表数据方法
     *
     * @return
     */
    public ObservableList<CustomerStrategyVO> getCusStrategyList() {
        return CusStrategyList;
    }


    /**
     * 将新增的客户回馈策略传回BL层
     * 洗数据！
     *
     * @param type
     * @param begin
     * @param end
     * @param level
     * @param Gifts
     * @param voucher
     * @param Discount
     * @return
     */
    public ResultMessage addCusStrategy(StrategyType type, LocalDate begin, LocalDate end, int level, ArrayList<GiftItem> Gifts, Voucher voucher
            , double Discount) {

        /**
         *若起始日期在当天日期前，则提示错误
         */
        if(!checker.checkBegin(begin)){
            return ResultMessage.FAIL;
        }


        /**
         * 若结束日期在起始日期前，则提示错误
         */
        if(!checker.checkEnd(begin,end)){
            return ResultMessage.FAIL;
        }


        /**
         * 若折让金额不为正，则提示错误
         */
        if(!checker.checkMoney(Discount)){
            return ResultMessage.FAIL;
        }

        CustomerStrategyVO strategy = new CustomerStrategyVO(type, begin, end, level, Gifts, voucher, Discount);
        ResultMessage isSuccess = ResultMessage.SUCCESS;
        try {
            isSuccess = blService.addCusStrategy(strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isSuccess == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAIL;
        }
    }


    /**
     * 新增组合降价策略
     * @param type
     * @param begin
     * @param end
     * @param pkgName
     * @param price
     * @param pkgGood
     * @return
     */
    public ResultMessage addPkgStrategy(StrategyType type, LocalDate begin, LocalDate end,String pkgName, double price,
                                        ArrayList<PackageGoodItem> pkgGood) {

        /**
         *若起始日期在当天日期前，则提示错误
         */
        if(!checker.checkBegin(begin)){
            return ResultMessage.FAIL;
        }


        /**
         * 若结束日期在起始日期前，则提示错误
         */
        if(!checker.checkEnd(begin,end)){
            return ResultMessage.FAIL;
        }

        PackageStrategyVO strategy=new PackageStrategyVO(type,begin,end,pkgName,price,pkgGood);
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.addPkgStrategy(strategy);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if (isSuccess == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAIL;
        }
    }


    public ResultMessage addAmouStrategy(StrategyType type, LocalDate begin, LocalDate end, double Amount, ArrayList<GiftItem> Gifts, Voucher voucher) {

        /**
         *若起始日期在当天日期前，则提示错误
         */
        if(!checker.checkBegin(begin)){
            return ResultMessage.FAIL;
        }


        /**
         * 若结束日期在起始日期前，则提示错误
         */
        if(!checker.checkEnd(begin,end)){
            return ResultMessage.FAIL;
        }



        AmountStrategyVO strategy = new AmountStrategyVO(type, begin, end, Amount, Gifts, voucher);
        ResultMessage isSuccess = ResultMessage.SUCCESS;
        try {
            isSuccess = blService.addAmouStrategy(strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isSuccess == ResultMessage.SUCCESS) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAIL;
        }
    }



        /**
         * 删除促销策略
         *
         * @return
         */
    public ResultMessage deleteStrategy(ObservableList<Integer> list) {

        ResultMessage isSuccess = ResultMessage.SUCCESS;
        for (Integer e : list) {
            try {
                isSuccess = blService.removeCusStrategy(CusStrategyList.get(e));
                if (isSuccess == ResultMessage.FAIL) {
                    return ResultMessage.FAIL;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            CusStrategyList.remove(e, e + 1);
        }

        return ResultMessage.SUCCESS;
    }

    public ObservableList<AmountStrategyVO> getAmouStrategyList() {
        return AmouStrategyList;
    }
}



