package BusinessLogic.CommodityBL;

import DataService.CommodityDataService;
import PO.*;
import Util.GoodTreeNodeType;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CommodityDataProvider implements CommodityDataService {

    private GoodTreeNodePO set(GoodTreeNodePO po, Integer index, Integer fatherIndex, ArrayList<Integer> sonIndexes){
        po.setIndex(index);
        po.setFatherIndex(fatherIndex);
        po.setSonIndexes(sonIndexes);

        return  po;
    }

    private ArrayList<GoodTreeNodePO> goodTree = null;

    /**
     * 获得当前的商品层次
     *
     * @return 一个GoodTreePO，打包了当前的商品层次
     * @throws RemoteException
     */
    @Override
    public GoodTreePO getGoodTree() throws RemoteException {

        //仅供测试
        GoodTreeNodePO root = new CategoryPO("001","root",null);
        GoodTreeNodePO category2 = new CategoryPO("002","1-1","001");
        GoodTreeNodePO category3 = new CategoryPO("003","1-2","001");
        GoodTreeNodePO category4 = new CategoryPO("004","1-2-1","003");
        GoodTreeNodePO category5 = new CategoryPO("005","1-2-2","003");
        GoodTreeNodePO category6 = new CategoryPO("006","1-2-3","003");
        GoodTreeNodePO good7 = new GoodPO("007", "1-2-2:1","005","A",100,
                10.0,30.0,140,9.8,28.0,"7");
        GoodTreeNodePO good8 = new GoodPO("008", "1-2-2:2","005","B",110,
                250.0,300.0,440,250.0,310.0,"8");

        set(root,0,-1, new ArrayList<>(Arrays.asList(1, 2)));
        set(category2,1,0,null);
        set(category3,2,0,new ArrayList<>(Arrays.asList(3,4,5)));
        set(category4,3,2,null);
        set(category5,4,2,new ArrayList<>(Arrays.asList(6,7)));
        set(category6,5,2,null);
        set(good7,6,4,null);
        set(good8,7,4,null);

        goodTree = new ArrayList<>(Arrays.asList(root,category2,category3,category4,category5,category6,good7,good8));

        return new GoodTreePO(goodTree);
    }

    /**
     * 添加一个新商品分类
     *
     * @param newCategoryPO 打包了新商品分类的信息
     * @return 一个CategoryPO，打包了新商品分类的父分类的信息
     * @throws RemoteException
     */
    @Override
    public ResultMessage addCategory(CategoryPO newCategoryPO) throws RemoteException {
        return null;
    }

    /**
     * 删除一个商品分类
     *
     * @param delCategoryID 要删除的商品分类的id
     * @return 一个ResultMessage
     * @throws RemoteException
     */
    @Override
    public ResultMessage delCategory(String delCategoryID) throws RemoteException {
        return null;
    }

    /**
     * 移动商品分类，修改商品分类名称
     *
     * @param categoryPO 商品分类id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateCategory(CategoryPO categoryPO) throws RemoteException {
        return null;
    }

    /**
     * 增加一个商品
     *
     * @param newGoodPO 打包了新商品的信息
     * @return 一个CategoryPO，打包了新的父分类的信息
     * @throws RemoteException
     */
    @Override
    public ResultMessage addGood(GoodPO newGoodPO) throws RemoteException {
        return null;
    }

    /**
     * 删除一个商品
     *
     * @param delGoodID 要删除的商品的id
     * @return 一个ResultMessage
     * @throws RemoteException
     */
    @Override
    public ResultMessage delGood(String delGoodID) throws RemoteException {
        return null;
    }

    /**
     * 移动一个商品，重命名一个商品，更新一个商品的型号、警戒数量、进价、售价
     *
     * @param goodPO 商品id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateGood(GoodPO goodPO) throws RemoteException {
        return null;
    }

    /**
     * 递归搜索商品分类下的商品
     *
     * @param input      搜索的关键字（可能是关键词，也可能是id的一部分）
     * @param CategoryID 在这个id相应的商品分类下搜索
     * @return 返回匹配的商品列表，若无匹配的商品，则返回空列表
     * @throws RemoteException
     */
    @Override
    public ArrayList<GoodPO> searchGood(String input, String CategoryID) throws RemoteException {
        return null;
    }

    /**
     * 查看一段时间内的库存变动情况
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 一个CommodityPO，打包了相时段的库存变动情况
     * @throws RemoteException
     */
    @Override
    public ArrayList<CommodityPO> getCommodity(LocalDate start, LocalDate end) throws RemoteException {
        return null;
    }


    /**
     * 更新一个商品的库存
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateAmount(String GoodID, int difference,double price,LocalDate date) throws RemoteException {
        return null;
    }

    /**
     * 更新一个商品的最近售价
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateRecentSellingPrice(String GoodID, double difference) throws RemoteException {
        return null;
    }

    /**
     * 更新一个商品的最近进价
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return
     */
    @Override
    public ResultMessage updateRecentBuyingPrice(String GoodID, double difference) throws RemoteException {
        return null;
    }

    /**
     * 查询相应的id的商品是否都存在
     *
     * @param idList id列表
     * @return 如果都存在返回SUCCESS，如果有至少一个不存在返回FAIL，如果发成异常返回ERROR
     */
    @Override
    public ResultMessage checkExistence(ArrayList<String> idList) throws RemoteException {
        return null;
    }

    /**
     * 用商品id找商品po
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public GoodPO goodsearchByID(String id) throws RemoteException {
        return null;
    }
}
