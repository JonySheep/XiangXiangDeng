package BusinessLogic.LogBL;

import Util.*;
import VO.*;

/**
 * Created by Jason on 2017/10/22.
 */
public interface LogBLInfo {
    /**
     * 写入一条系统日志
     * @param log 由LogVO包装的一条系统日志
     * @return 一个ResultMessage
     */
    ResultMessage log(LogVO log);
}
