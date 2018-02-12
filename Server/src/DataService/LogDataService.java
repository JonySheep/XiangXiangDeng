package DataService;

import PO.LogPO;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 */
public interface LogDataService extends Remote {
    /**
     * 添加一条系统日志
     * @param log
     * @return
     * @throws RemoteException
     */
    ResultMessage log(LogPO log) throws RemoteException;

    /**
     * 读取所有的系统日志
     * @return
     * @throws RemoteException
     */
    ArrayList<LogPO> read() throws RemoteException;
}
