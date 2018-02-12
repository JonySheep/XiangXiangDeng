package BusinessLogic.CommodityBL;

import DataService.CommodityDataService;
import PO.*;
import Rmi.RemoteHelper;
import Util.ResultMessage;
import VO.CategoryVO;
import VO.CommodityVO;
import VO.GoodTreeVO;
import VO.GoodVO;

import java.io.File;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jason on 2017/11/14.
 */
class Commodity {

    /**
     * 应用了单例模式
     */
    private static Commodity ourInstance = new Commodity();

    //链接服务器测试
    private static CommodityDataService commodityData = RemoteHelper.getInstance().getCommodityDataService();

    //链接DataProvider测试
    //private static CommodityDataService commodityData = new CommodityDataProvider();


    private static final CategoryVO emptyCategoryVO = new CategoryVO();
    private static final GoodVO emptyGoodVO = new GoodVO();

    static Commodity getInstance() {
        return ourInstance;
    }

    private Commodity() {
    }


    /**
     * 获得当前的商品层次
     *
     * @return 一个GoodTreeVO，打包了当前的商品层次
     */

    GoodTreeVO getGoodTree() {
        try {
            GoodTreePO po = commodityData.getGoodTree();
            return new GoodTreeVO(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增加一个商品分类
     *
     * @param newCategoryVO 打包了新商品分类的信息
     * @return 一个ResultMessage
     */

    ResultMessage addCategory(CategoryVO newCategoryVO) {
        //取出数据
        String id = newCategoryVO.getId();
        String name = newCategoryVO.getName();
        String fatherCategoryId = newCategoryVO.getFatherCategoryId();


        //把VO转化为PO
        CategoryPO newCategoryPO = new CategoryPO(name, fatherCategoryId);

        //调用commodityData.addCategory
        try {
            return commodityData.addCategory(newCategoryPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }

    }

    /**
     * 删除一个商品分类
     *
     * @param delCategoryID 要删除的商品分类的id
     * @return 一个ResultMessage
     */

    ResultMessage delCategory(String delCategoryID) {
        //验证数据是否合法
        if (delCategoryID == emptyCategoryVO.getId()) {//商品分类id不能为空
            return ResultMessage.ILLEGAL_INPUT;
        }

        //调用commodityData.delCategory
        try {
            return commodityData.delCategory(delCategoryID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }

    }

    /**
     * 移动商品分类，修改商品分类名称
     *
     * @param categoryVO 商品分类id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */

    ResultMessage updateCategory(CategoryVO categoryVO) {
        //获得vo数据
        String id = categoryVO.getId();
        String name = categoryVO.getName();
        String fatherCategoryId = categoryVO.getFatherCategoryId();

        //验证数据是否合法
        if (id != emptyCategoryVO.getId() //商品id不能为空
                && (name != emptyCategoryVO.getId() || fatherCategoryId != emptyCategoryVO.getFatherCategoryId()))/*至少修改一个*/ {
        } else {
            return ResultMessage.ILLEGAL_INPUT;
        }

        //把VO转化为PO
        CategoryPO po = new CategoryPO(id, name, fatherCategoryId);

        //调用commodityData.updateCategory
        try {
            return commodityData.updateCategory(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }

    }

    /**
     * 增加一个商品
     *
     * @param newGoodVO 打包了新商品的信息
     * @return 一个ResultMessage
     */

    ResultMessage addGood(GoodVO newGoodVO) {
        //获得vo数据
        String name = newGoodVO.getName();
        String fatherCategoryId = newGoodVO.getFatherCategoryId();
        String type = newGoodVO.getType();
        Integer warningAmount = newGoodVO.getWarningAmount();
        Double buyingPrice = newGoodVO.getBuyingPrice();
        Double sellingPrice = newGoodVO.getSellingPrice();
        String comment = newGoodVO.getComment();

        //vo转化为po
        GoodPO po = new GoodPO(name, fatherCategoryId, type, warningAmount, buyingPrice, sellingPrice, comment);

        //调用commodityData
        try {
            return commodityData.addGood(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 删除一个商品
     *
     * @param delGoodID 要删除的商品的id
     * @return 一个ResultMessage
     */

    ResultMessage delGood(String delGoodID) {
        //验证数据是否合法
        if (delGoodID == emptyGoodVO.getId()) {//商品分类id不能为空
            return ResultMessage.ILLEGAL_INPUT;
        }

        //调用commodityData.delCategory
        try {
            return commodityData.delGood(delGoodID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 移动一个商品，重命名一个商品，更新一个商品的型号、警戒数量、进价、售价
     *
     * @param goodVO 商品id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */

    ResultMessage updateGood(GoodVO goodVO) {
        //获得vo数据
        String id = goodVO.getId();
        String name = goodVO.getName();
        String fatherCategoryId = goodVO.getFatherCategoryId();
        String type = goodVO.getType();
        Integer warningAmount = goodVO.getWarningAmount();
        Double buyingPrice = goodVO.getBuyingPrice();
        Double sellingPrice = goodVO.getSellingPrice();
        Integer nowAmount = goodVO.getNowAmount();
        Double recentBuyingPrice = goodVO.getRecentBuyingPrice();
        Double recentSellingPrice = goodVO.getRecentSellingPrice();
        String comment = goodVO.getComment();

        //验证数据是否合法
        if (id != emptyGoodVO.getId()
                && (name != emptyGoodVO.getName()
                || fatherCategoryId != emptyGoodVO.getFatherCategoryId()
                || type != emptyGoodVO.getType()
                || warningAmount != emptyGoodVO.getWarningAmount()
                || buyingPrice != emptyGoodVO.getBuyingPrice()
                || sellingPrice != emptyGoodVO.getSellingPrice())) {
        } else {
            return ResultMessage.ILLEGAL_INPUT;
        }

        //把vo转化为po
        GoodPO goodPO = new GoodPO(
                id,
                name,
                fatherCategoryId,
                type,
                warningAmount,
                buyingPrice,
                sellingPrice,
                nowAmount,
                recentBuyingPrice,
                recentSellingPrice,
                comment
        );

        //调用commodityData
        try {
            return commodityData.updateGood(goodPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 递归搜索商品分类下的商品
     *
     * @param input      搜索的关键字（可能是关键词，也可能是id的一部分）
     * @param CategoryID 在这个id对应的商品分类下搜索
     * @return 返回匹配的商品列表，若无匹配的商品，则返回空列表
     */

    ArrayList<GoodVO> searchGood(String input, String CategoryID) {
        try {
            ArrayList<GoodPO> list = commodityData.searchGood(input, CategoryID);
            ArrayList<GoodVO> ret = new ArrayList<>();
            for (GoodPO po : list) {
                ret.add(new GoodVO(po));
            }
            return ret;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 要查看一段时间内的库存变动情况
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 返回相应的库存变动情况
     */

    ArrayList<CommodityVO> getCommodity(LocalDate start, LocalDate end) {
        try {
            ArrayList<CommodityPO> list = commodityData.getCommodity(start, end);
            ArrayList<CommodityVO> ret = new ArrayList<>();
            for (CommodityPO po : list) {
                ret.add(new CommodityVO(po));
            }
            return ret;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 导出一段时间内的库存变动情况为EXCEL表格形式
     *
     * @param filepath 路径
     * @return 一个ResultMessage
     */

    ResultMessage export(File filepath) {
        //TODO
        return null;
    }

    /**
     * 查询相应的id的商品是否都存在
     * @param idList id列表
     * @return 如果都存在返回SUCCESS，如果有至少一个不存在返回FAIL，如果发生异常返回ERROR
     */

    ResultMessage checkExistence(ArrayList<String> idList){
        try {
            return commodityData.checkExistence(idList);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     * 更新一个商品的库存
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */

    ResultMessage updateAmount(String GoodID, int difference, double price, LocalDate date) {
        try {
            return commodityData.updateAmount(GoodID, difference,price,date);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 更新一个商品的最近售价
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */

    ResultMessage updateRecentSellingPrice(String GoodID, double difference) {
        try {
            return commodityData.updateRecentSellingPrice(GoodID, difference);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 更新一个商品的最近进价
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */

    ResultMessage updateRecentBuyingPrice(String GoodID, double difference) {
        try {
            return commodityData.updateRecentBuyingPrice(GoodID, difference);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 用id获取GoodVO
     * @param id
     * @return
     */
    public GoodVO goodsearchByID(String id) {
        try{
            GoodPO po =  commodityData.goodsearchByID(id);
            return new GoodVO(po);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }
}
