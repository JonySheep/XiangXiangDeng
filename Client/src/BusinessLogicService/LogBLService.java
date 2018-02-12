package BusinessLogicService;

import Util.ResultMessage;
import VO.*;

import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 */
public interface LogBLService {
    /**
     * 写入一条系统日志
     * @param log 由LogVO包装的一条系统日志
     * @return 一个ResultMessage
     */
    ResultMessage log(LogVO log);

    /**
     * 读取全部的系统日志
     * @return 所有的系统日志
     */
    ArrayList<LogVO> read();
}
