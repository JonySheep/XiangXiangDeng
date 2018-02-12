package DataStub;

import DataService.AccountDataService;
import PO.AccountPO;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/23.
 */
public class AccountData_stub implements AccountDataService {


    @Override
    public ResultMessage disguiseDeleteAccount(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
        return null;
    }

    @Override
    public AccountPO getInfo(String cardName) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<AccountPO> search(String str) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<AccountPO> getAllAccounts() throws RemoteException {
        return null;
    }
}
