package PO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 */
public class BookPO implements Serializable {
    ArrayList<GoodPO> goodsList;

    ArrayList<CustomerPO> clientList;

    ArrayList<AccountPO> accountList;

    ArrayList<CommodityPO> commodityList;

    public BookPO(ArrayList<GoodPO> goodsList,ArrayList<CustomerPO> clientList,ArrayList<AccountPO> accountList,ArrayList<CommodityPO> commodityList)
    {
        this.goodsList = goodsList;
        this.clientList = clientList;
        this.accountList = accountList;
        this.commodityList = commodityList;
    }

}
