package DataService;

import PO.CustomerPO;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/23.
 */
public interface CustomerDataService extends Remote
{
    /**
     *
     * 新增一个客户
     * @param po
     * @return
     * @throws RemoteException
     */
    ResultMessage insert(CustomerPO po) throws RemoteException;

    /**
     *
     * 删除一个客户
     * @param id
     * @return
     * @throws RemoteException
     */
    ResultMessage disguiseDeleteCustomer(String id) throws RemoteException;

    /**
     *
     *  更新客户信息
     * @param po
     * @return
     * @throws RemoteException
     */
    ResultMessage update(CustomerPO po) throws RemoteException;

    /**
     *
     *  根据输入字符模糊查找
     * @param str 输入的字符
     * @return
     * @throws RemoteException
     */
    ArrayList<CustomerPO> searchCustomers(String str) throws RemoteException;

    /**
     *
     * 根据输入id单一定位查找
     * @param id
     * @return
     * @throws RemoteException
     */
    CustomerPO searchByID(String id) throws RemoteException;

    /**
     *
     * 获得所有供应商客户
     * @return
     * @throws RemoteException
     */
    ArrayList<CustomerPO> getSupplier() throws RemoteException;

    /**
     * 获得所有销售商客户
     * @return
     * @throws RemoteException
     */
    ArrayList<CustomerPO> getSaler() throws RemoteException;

    /**
     * 获得所有客户
     * @return
     * @throws RemoteException
     */
    ArrayList<CustomerPO> getCustomer() throws RemoteException;
}
