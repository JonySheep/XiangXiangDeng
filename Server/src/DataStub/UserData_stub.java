package DataStub;

import DataService.UserDataService;
import PO.UserPO;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/23.
 */
public class UserData_stub implements UserDataService {
    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage disguiseDelete(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        return null;
    }

    @Override
    public UserPO find(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage login(String id, String password) throws RemoteException {
        return null;
    }

    @Override
    public boolean logout(String id) throws RemoteException {
        return false;
    }

    @Override
    public boolean resetPassword(String id, String oldPassword, String newPassword) throws RemoteException {
        return false;
    }

    @Override
    public ArrayList<UserPO> getAllUser() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<UserPO> findUsers(String str) throws RemoteException {
        return null;
    }


}
