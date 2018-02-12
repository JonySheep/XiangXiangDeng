package Rmi;

import DataService.*;

import java.rmi.Remote;

/**
 * Created by Jason on 2017/10/22.
 */
import DataService.CommodityDataService;

import java.rmi.Remote;

public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();

    public static RemoteHelper getInstance() {
        return remoteHelper;
    }

    private RemoteHelper() {
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public CommodityDataService getCommodityDataService() {
        return (CommodityDataService) remote;
    }

    public CustomerDataService getCustomerDataService() {
        return (CustomerDataService) remote;
    }

    public StrategyDataService getStrategyDataService() {
        return (StrategyDataService) remote;
    }

    public UserDataService getUserDataService(){
        return (UserDataService) remote;
    }

    public AccountDataService getAccountDataService(){
        return (AccountDataService) remote;
    }

    public DocDataService getDocDataService() {
        return (DocDataService) remote;
    }


}
