package DataStub;

import DataService.LogDataService;
import PO.LogPO;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 */
public class LogData_stub implements LogDataService {
    /**
     * 应用了单例模式
     */
    private static LogData_stub ourInstance = new LogData_stub();

    public static LogData_stub getInstance() {
        return ourInstance;
    }

    private LogData_stub() {
    }

    /**
     * 添加一条系统日志
     * @param log
     * @return
     * @throws RemoteException
     */
    @Override
    public synchronized ResultMessage log(LogPO log) throws RemoteException {
        return null;
    }

    /**
     * 读取所有的系统日志
     * @return
     * @throws RemoteException
     */
    @Override
    public synchronized ArrayList<LogPO> read() throws RemoteException {
        return null;
    }
}
