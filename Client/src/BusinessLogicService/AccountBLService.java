package BusinessLogicService;

import Util.ResultMessage;
import VO.AccountVO;

import java.util.ArrayList;

/**
 * Created by Jeven on 2017/10/22.
 */
public interface AccountBLService
{
    /**
     *
     * @return 返回数据库内所有账户组成的列表
     */
    ArrayList<AccountVO> getAccountList();

    /**
     *
     * @param accountvo 指一个AccountVO对象
     * @return 返回一个ResultMessage
     */
    ResultMessage newAccount(AccountVO accountvo);

    /**
     *
     * @param input 表示界面输入栏中输入的字符串
     * @return 返回符合要求的Account列表
     */
    ArrayList<AccountVO> search(String input);

    /**
     *
     * @param accountvo 表示一个Account在界面上显示的信息
     * @return 返回一个ResultMessage表示删除结果
     */
    ResultMessage delete(AccountVO accountvo);

    /**
     *
     * 根据vo修改账户
     * @param accountVO
     * @return
     */
    ResultMessage update(AccountVO accountVO);
}
