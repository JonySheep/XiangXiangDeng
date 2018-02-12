package BusinessLogic.StrategyBL;

import Util.GiftItem;
import Util.GoodSelectItem;
import Util.SelectedGoodItem;
import VO.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface StrategyBLInfo {

    /**
     * 根据客户等级拿到相应策略
     * @param level
     * @param now
     * @return
     */
    public CustomerStrategyVO getCustomerStrategy(int level, LocalDate now);

    /**
     * 根据总价拿到相应策略
     * @param sum
     * @param now
     * @return
     */
    public AmountStrategyVO getAmountStrategy(double sum, LocalDate now);

    /**
     * 根据卖的东西获得相应策略，重新计算优惠的商品的单价，返回修改过的列表
     * @param goodSelectItems
     * @param now
     * @return
     */
    public ArrayList<GoodSelectItem> recalcPrice(ArrayList<GoodSelectItem> goodSelectItems, LocalDate now);


}