package DataService;

import PO.AmountStrategyPO;
import PO.CustomerStrategyPO;
import PO.PackageStrategyPO;
import PO.StrategyPO;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface StrategyDataService extends Remote {

    /**
     * 新增客户回馈策略
     * @param cusstrategypo
     * @return
     * @throws RemoteException
     */
    ResultMessage insertCusStrategy(CustomerStrategyPO cusstrategypo) throws RemoteException;


    /**
     * 新增组合降价策略
     * @param pkgstrategypo
     * @return
     * @throws RemoteException
     */
    ResultMessage insertPkgStrategy(PackageStrategyPO pkgstrategypo) throws RemoteException;


    /**
     * 新增总额赠送策略
     * @param amoustrategypo
     * @return
     * @throws RemoteException
     */
    ResultMessage insertAmouStrategy(AmountStrategyPO amoustrategypo) throws RemoteException;


    /**
     * 删除促销策略
     * @param strategypo
     * @return
     * @throws RemoteException
     */
    ResultMessage delete(StrategyPO strategypo) throws RemoteException;


    /**
     * 根据策略ID得到该促销策略
     * @param id
     * @return
     * @throws RemoteException
     */
    StrategyPO getStrategy(String id) throws RemoteException;


    /**
     * 得到当前可使用的客户回馈策略
     * @param date
     * @param level
     * @return
     * @throws RemoteException
     */
    CustomerStrategyPO getCurrentCusStrategy(LocalDate date, int level) throws RemoteException;


    /**
     * 得到当前可使用的组合降价策略
     * @param date
     * @return
     * @throws RemoteException
     */
    ArrayList<PackageStrategyPO> getCurrentPkgStrategy(LocalDate date) throws RemoteException;


    /**
     * 得到当前可使用的总额赠送策略
     * @param date
     * @param amount
     * @return
     * @throws RemoteException
     */
    AmountStrategyPO getCurrentAmouStrategy(LocalDate date, double amount) throws RemoteException;


    /**
     * 得到所有客户回馈策略（按id顺序
     * @return
     * @throws RemoteException
     */
    ArrayList<CustomerStrategyPO> getCusStrategy() throws RemoteException;


    /**
     * 得到所有组合降价策略（按id顺序
     * @return
     * @throws RemoteException
     */
    ArrayList<PackageStrategyPO> getPkgStrategy() throws RemoteException;


    /**
     * 得到所有总额赠送策略（按id顺序
     * @return
     * @throws RemoteException
     */
    ArrayList<AmountStrategyPO> getAmouStrategy() throws RemoteException;

    ResultMessage updateCusStrategy(CustomerStrategyPO customerStrategyPO) throws RemoteException;

    ResultMessage updatePkgStrategy(PackageStrategyPO packageStrategyPO) throws RemoteException;

    ResultMessage updateAmouStrategy(AmountStrategyPO amountStrategyPO) throws RemoteException;
}
