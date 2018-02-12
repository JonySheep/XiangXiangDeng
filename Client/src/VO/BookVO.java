package VO;

import PO.AccountPO;
import PO.CommodityPO;
import PO.CustomerPO;
import PO.GoodPO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/22.
 */
public class BookVO
{
    ArrayList<GoodVO> goodsList;

    ArrayList<CustomerVO> clientList;

    ArrayList<AccountVO> accountList;

    ArrayList<CommodityVO> commodityList;

    public BookVO(ArrayList<GoodVO> goodsList,ArrayList<CustomerVO> clientList,ArrayList<AccountVO> accountList,ArrayList<CommodityVO> commodityList)
    {
        this.goodsList = goodsList;
        this.clientList = clientList;
        this.accountList = accountList;
        this.commodityList = commodityList;
    }

}
