package BusinessLogic.LogBL;

import BusinessLogicService.LogBLService;
import Util.OperationType;
import Util.ResultMessage;
import VO.LogVO;

import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 */
public class LogBL_stub implements LogBLService, LogBLInfo {

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

    /**
     * 读取全部的系统日志
     *
     * @return 所有的系统日志
     */
    @Override
    public ArrayList<LogVO> read() {
        ArrayList<LogVO> ret = new ArrayList<>();
        for(int i = 0; i<10; i++){
            ret.add(new LogVO(OperationType.EMPTY,"User"+i,"Remark"+i));
        }
        return ret;
    }
}
