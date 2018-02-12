package BusinessLogic.CommodityBL;

import Util.ResultMessage;
import VO.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 */
public interface CommodityBLInfo {
    /**
     * 获得当前的商品层次
     * @return 一个GoodTreeVO，打包了当前的商品层次
     */
    GoodTreeVO getGoodTree( );

    /**
     * 查询相应的id的商品是否都存在
     * @param idList id列表
     * @return 如果都存在返回SUCCESS，如果有至少一个不存在返回FAIL，出现异常返回ERROR
     */
    ResultMessage checkExistence(ArrayList<String> idList);

    /**
     * 更新一个商品的库存
     * @param GoodID 商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    ResultMessage updateAmount(String GoodID, int difference, double price, LocalDate date);

    /**
     * 更新一个商品的最近售价
     * @param GoodID 商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    ResultMessage updateRecentSellingPrice(String GoodID, double difference);

    /**
     * 更新一个商品的最近进价
     * @param GoodID 商品编号
     * @param difference 变化值，可正可负
     * @return
     */
    ResultMessage updateRecentBuyingPrice(String GoodID, double difference);


    /**
     * 用id获得GoodVO
     * @param id
     * @return
     */
    GoodVO goodsearchByID(String id);
}
