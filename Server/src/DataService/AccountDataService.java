package DataService;

import PO.AccountPO;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Jeven on 2017/10/23.
 */
public interface AccountDataService extends Remote
{
    /**
     *
     * @param id
     * @return 返回删除结果
     * @throws RemoteException
     */
    ResultMessage disguiseDeleteAccount(String id) throws RemoteException;

    /**
     *
     * @param po
     * @return 返回新增结果
     * @throws RemoteException
     */
    ResultMessage insert(AccountPO po) throws RemoteException;

    /**
     *
     * @param po
     * @return 返回更新结果
     * @throws RemoteException
     */
    ResultMessage update(AccountPO po) throws RemoteException;

    /**
     *
     * @param cardName
     * @return 返回找到的Account信息（唯一确定）
     * @throws RemoteException
     */
    AccountPO getInfo(String cardName) throws RemoteException;

    /**
     *
     * @param str
     * @return 返回模糊查找的结果（不唯一）
     * @throws RemoteException
     */
    ArrayList<AccountPO> search(String str) throws RemoteException;


    ArrayList<AccountPO> getAllAccounts() throws RemoteException;

}
