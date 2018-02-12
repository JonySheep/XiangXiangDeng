package DataService;

import PO.CategoryPO;
import PO.CommodityPO;
import PO.GoodPO;
import PO.GoodTreePO;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Jason on 2017/10/22.
 * Updated by Jason on 2017/11/10.
 */
public interface CommodityDataService extends Remote{

    /**
     * 获得当前的商品层次
     * @return 一个GoodTreePO，打包了当前的商品层次
     * @throws RemoteException
     */
    GoodTreePO getGoodTree() throws RemoteException;

    /**
     * 添加一个新商品分类
     * @param newCategoryPO 打包了新商品分类的信息
     * @return 一个CategoryPO，打包了新商品分类的父分类的信息
     * @throws RemoteException
     */
    ResultMessage addCategory(CategoryPO newCategoryPO) throws RemoteException;

    /**
     * 删除一个商品分类
     * @param delCategoryID 要删除的商品分类的id
     * @return 一个ResultMessage
     * @throws RemoteException
     */
    ResultMessage delCategory(String delCategoryID) throws RemoteException;

    /**
     * 移动商品分类，修改商品分类名称
     * @param categoryPO 商品分类id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    ResultMessage updateCategory(CategoryPO categoryPO) throws RemoteException;

    /**
     * 增加一个商品
     * @param newGoodPO 打包了新商品的信息
     * @return 一个CategoryPO，打包了新的父分类的信息
     * @throws RemoteException
     */
    ResultMessage addGood(GoodPO newGoodPO) throws RemoteException;

    /**
     * 删除一个商品
     * @param delGoodID 要删除的商品的id
     * @return 一个ResultMessage
     * @throws RemoteException
     */
    ResultMessage delGood(String delGoodID) throws RemoteException;

    /**
     * 移动一个商品，重命名一个商品，更新一个商品的型号、警戒数量、进价、售价
     * @param goodPO 商品id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    ResultMessage updateGood(GoodPO goodPO) throws RemoteException;

    /**
     * 递归搜索商品分类下的商品
     * @param input 搜索的关键字（可能是关键词，也可能是id的一部分）
     * @param CategoryID 在这个id相应的商品分类下搜索
     * @return 返回匹配的商品列表，若无匹配的商品，则返回空列表
     * @throws RemoteException
     */
    ArrayList<GoodPO> searchGood(String input, String CategoryID) throws RemoteException;

    /**
     * 查看一段时间内的库存变动情况
     * @param start 开始时间
     * @param end 结束时间
     * @return 一个CommodityPO，打包了相时段的库存变动情况
     * @throws RemoteException
     */
    ArrayList<CommodityPO> getCommodity(LocalDate start, LocalDate end) throws RemoteException;

    /**
     * 更新一个商品的库存
     * @param GoodID 商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    ResultMessage updateAmount(String GoodID, int difference, double price, LocalDate date) throws RemoteException;

    /**
     * 更新一个商品的最近售价
     * @param GoodID 商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    ResultMessage updateRecentSellingPrice(String GoodID, double difference) throws RemoteException;

    /**
     * 更新一个商品的最近进价
     * @param GoodID 商品编号
     * @param difference 变化值，可正可负
     * @return
     */
    ResultMessage updateRecentBuyingPrice(String GoodID, double difference) throws RemoteException;

    /**
     * 查询相应的id的商品是否都存在
     *
     * @param idList id列表
     * @return 如果都存在返回SUCCESS，如果有至少一个不存在返回FAIL，如果发成异常返回ERROR
     */
    ResultMessage checkExistence(ArrayList<String> idList) throws RemoteException;

    public GoodPO goodsearchByID(String id)  throws RemoteException;


}
