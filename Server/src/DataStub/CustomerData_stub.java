package DataStub;

import DataService.CustomerDataService;
import PO.CustomerPO;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/23.
 */
public class CustomerData_stub implements CustomerDataService {

    @Override
    public ResultMessage insert(CustomerPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage disguiseDeleteCustomer(String id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(CustomerPO po) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CustomerPO> searchCustomers(String str) throws RemoteException {
        return null;
    }

    @Override
    public CustomerPO searchByID(String id) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CustomerPO> getSupplier() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CustomerPO> getSaler() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CustomerPO> getCustomer() throws RemoteException {
        return null;
    }
}

