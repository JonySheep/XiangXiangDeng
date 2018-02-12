package BusinessLogic.LogBL;

import Util.ResultMessage;
import VO.LogVO;

public class Log_mock implements LogBLInfo {
    /**
     * 写入一条系统日志
     *
     * @param log 由LogVO包装的一条系统日志
     * @return 一个ResultMessage
     */
    @Override
    public ResultMessage log(LogVO log) {
        return ResultMessage.SUCCESS;
    }
}
