package BusinessLogic.CommodityBL;

import BusinessLogicService.CommodityBLService;
import Util.ResultMessage;
import VO.CategoryVO;
import VO.CommodityVO;
import VO.GoodTreeVO;
import VO.GoodVO;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jason on 2017/11/14.
 */
public class CommodityController implements CommodityBLService, CommodityBLInfo{

    /**
     * 应用了单例模式
     */
    private static CommodityController ourInstance = null;
    private static Commodity commodity = Commodity.getInstance();

    public static CommodityController getInstance() {
        if(ourInstance ==null){
            ourInstance = new CommodityController();
        }
        return ourInstance;
    }

    private CommodityController() {
    }

    /**
     * 获取当前的商品层次
     *
     * @return 一个GoodTreeVO，打包了当前的商品层次
     */
    @Override
    public GoodTreeVO getGoodTree() {
        return commodity.getGoodTree();
    }

    /**
     * 查询相应的id的商品是否都存在
     *
     * @param idList id列表
     * @return 如果都存在返回SUCCESS，如果有至少一个不存在返回FAIL，如果发成异常返回ERROR
     */
    @Override
    public ResultMessage checkExistence(ArrayList<String> idList) {
        return commodity.checkExistence(idList);
    }

    /**
     * 更新一个商品的库存
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateAmount(String GoodID, int difference, double price, LocalDate date) {
        return commodity.updateAmount(GoodID, difference,price,date);
    }

    /**
     * 更新一个商品的最近售价
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateRecentSellingPrice(String GoodID, double difference) {
        return commodity.updateRecentSellingPrice(GoodID, difference);
    }

    /**
     * 更新一个商品的最近进价
     *
     * @param GoodID     商品编号
     * @param difference 变化值，可正可负
     * @return
     */
    @Override
    public ResultMessage updateRecentBuyingPrice(String GoodID, double difference) {
        return commodity.updateRecentBuyingPrice(GoodID, difference);
    }

    /**
     * 用商品id找商品vo
     * @param id
     * @return
     */
    @Override
    public GoodVO goodsearchByID(String id) {
        return commodity.goodsearchByID(id);
    }

    /**
     * 增加一个商品分类
     *
     * @param newCategoryVO 打包了新商品分类的信息
     * @return 一个CategoryVO，打包了新商品父分类的信息
     */
    @Override
    public ResultMessage addCategory(CategoryVO newCategoryVO) {
        return commodity.addCategory(newCategoryVO);
    }

    /**
     * 删除一个商品分类
     *
     * @param delCategoryID 要删除的商品分类的id
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage delCategory(String delCategoryID) {
        return commodity.delCategory(delCategoryID);
    }

    /**
     * 移动商品分类，修改商品分类名称
     *
     * @param categoryVO 商品分类id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateCategory(CategoryVO categoryVO) {
        return commodity.updateCategory(categoryVO);
    }

    /**
     * 增加一个商品
     *
     * @param newGoodVO   打包了新商品的信息
     * @return 一个CategoryVO，打包了新商品父分类的信息
     */
    @Override
    public ResultMessage addGood(GoodVO newGoodVO) {
        return commodity.addGood(newGoodVO);
    }

    /**
     * 删除一个商品
     *
     * @param delGoodID 要删除的商品的id
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage delGood(String delGoodID) {
        return commodity.delGood(delGoodID);
    }

    /**
     * 移动一个商品，重命名一个商品，更新一个商品的型号、警戒数量、进价、售价
     *
     * @param goodVO 商品id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage updateGood(GoodVO goodVO) {
        return commodity.updateGood(goodVO);
    }

    /**
     * 递归搜索商品分类下的商品
     *
     * @param input      搜索的关键字（可能是关键词，也可能是id的一部分）
     * @param CategoryID 在这个id对应的商品分类下搜索
     * @return 返回匹配的商品列表，若无匹配的商品，则返回空列表
     */
    @Override
    public ArrayList<GoodVO> searchGood(String input, String CategoryID) {
        return commodity.searchGood(input, CategoryID);
    }

    /**
     * 要查看一段时间内的库存变动情况
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 返回相应的库存变动情况
     */
    @Override
    public ArrayList<CommodityVO> getCommodity(LocalDate start, LocalDate end) {
        return commodity.getCommodity(start,end);
    }

    /**
     * 导出一段时间内的库存变动情况为EXCEL表格形式
     *
     * @param filepath 路径
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage export(File filepath) {
        return commodity.export(filepath);
    }
}
