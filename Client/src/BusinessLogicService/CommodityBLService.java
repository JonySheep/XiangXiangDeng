package BusinessLogicService;

import Util.*;
import VO.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jason on 2017/10/22.
 * Updated by Jason on 2017/11/10.
 */
public interface CommodityBLService {
    /**
     * 获取当前的商品层次
     * @return 一个GoodTreeVO，打包了当前的商品层次
     */
    GoodTreeVO getGoodTree();

    /**
     * 增加一个商品分类
     * @param newCategoryVO 打包了新商品分类的信息
     * @return 一个ResultMessage
     */
    ResultMessage addCategory(CategoryVO newCategoryVO);

    /**
     * 删除一个商品分类
     * @param delCategoryID 要删除的商品分类的id
     * @return 一个ResultMessage
     */
    ResultMessage delCategory(String delCategoryID);

    /**
     * 移动商品分类，修改商品分类名称
     * @param categoryVO 商品分类id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    ResultMessage updateCategory(CategoryVO categoryVO);

    /**
     * 增加一个商品
     * @param newGoodVO 打包了新商品的信息
     * @return 一个ResultMessage
     */
    ResultMessage addGood(GoodVO newGoodVO);

    /**
     * 删除一个商品
     * @param delGoodID 要删除的商品的id
     * @return 一个ResultMessage
     */
    ResultMessage delGood(String delGoodID);

    /**
     * 移动一个商品，重命名一个商品，更新一个商品的型号、警戒数量、进价、售价
     * @param goodVO 商品id必须指明，其余发生更改的字段为更新值，未发生更改的字段为类内初始值
     * @return 一个ResultMessage
     */
    ResultMessage updateGood(GoodVO goodVO);

    /**
     * 递归搜索商品分类下的商品
     * @param input 搜索的关键字（可能是关键词，也可能是id的一部分）
     * @param CategoryID 在这个id对应的商品分类下搜索
     * @return 返回匹配的商品列表，若无匹配的商品，则返回空列表
     */
    ArrayList<GoodVO> searchGood(String input, String CategoryID);

    /**
     * 要查看一段时间内的库存变动情况
     * @param start 开始时间
     * @param end 结束时间
     * @return 返回相应的库存变动情况
     */
    ArrayList<CommodityVO> getCommodity(LocalDate start, LocalDate end);


    /**
     * 导出一段时间内的库存变动情况为EXCEL表格形式
     * @param filepath 路径
     * @return 一个ResultMessage
     */
    ResultMessage export(File filepath);
}
