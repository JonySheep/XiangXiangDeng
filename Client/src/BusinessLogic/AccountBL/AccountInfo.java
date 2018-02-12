package BusinessLogic.AccountBL;

import Util.ResultMessage;
import VO.AccountVO;

public interface AccountInfo {

    /**
     *
     * @param id id指账户编号
     * @return 返回id所对应的客户信息
     */
    AccountVO getInfo(String id);

    /**
     *
     * @param amount  表示账户余额
     * @return 返回一个ResultMMessage表示更新结果
     */
    ResultMessage updateBalance(double amount, String carNumber);
}
