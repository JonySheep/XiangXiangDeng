package BusinessLogic.LogBL;

import BusinessLogicService.LogBLService;
import Util.ResultMessage;
import VO.LogVO;

import java.util.ArrayList;

public class LogController implements LogBLService, LogBLInfo {
    private static LogController ourInstance = new LogController();
    private static Log logInstance = Log.getInstance();

    public static LogController getInstance() {
        return ourInstance;
    }

    private LogController() {
    }

    /**
     * 写入一条系统日志
     *
     * @param log 由LogVO包装的一条系统日志
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage log(LogVO log) {
        return logInstance.log(log);
    }

    /**
     * 读取全部的系统日志
     *
     * @return 所有的系统日志
     */
    @Override
    public ArrayList<LogVO> read() {
        return logInstance.read();
    }
}
