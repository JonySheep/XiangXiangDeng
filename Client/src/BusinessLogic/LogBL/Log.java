package BusinessLogic.LogBL;

import Util.ResultMessage;
import VO.LogVO;

import java.util.ArrayList;

public class Log{
    private static Log ourInstance = new Log();

    public static Log getInstance() {
        return ourInstance;
    }

    private Log() {
    }

    /**
     * 写入一条系统日志
     *
     * @param log 由LogVO包装的一条系统日志
     * @return 一个ResultMessage
     */
    public ResultMessage log(LogVO log) {
        return null;
    }

    /**
     * 读取全部的系统日志
     *
     * @return 所有的系统日志
     */
    public ArrayList<LogVO> read() {
        return null;
    }
}
