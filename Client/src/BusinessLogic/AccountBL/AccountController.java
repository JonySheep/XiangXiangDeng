
package BusinessLogic.AccountBL;

import BusinessLogicService.AccountBLService;
import Util.ResultMessage;
import VO.AccountVO;

import java.util.ArrayList;

public class AccountController implements AccountInfo, AccountBLService{
    Account account;

    /**
     * 重载AccountController构造函数
     * AccountControll与Account组合关系
     */
    public AccountController(){
        account = new Account();
    }

    /**
     *
     * @param id id指账户编号
     * @return 返回对应账户的信息
     */
    @Override
    public AccountVO getInfo(String id) {
        return account.getInfo(id);
    }

    /**
     *
     * @param amount  表示账户余额
     * @return 返回更新结果
     */
    @Override
    public ResultMessage updateBalance(double amount,String carNumber) {
        return account.updateBalance(amount,carNumber);
    }

    /**
     *
     * @return 返回账户列表
     */
    @Override
    public ArrayList<AccountVO> getAccountList() {
        return account.getAccountList();
    }

    /**
     *
     * @param accountvo 指一个AccountVO对象
     * @return 返回新增结果
     */
    @Override
    public ResultMessage newAccount(AccountVO accountvo) {
        return account.newAccount(accountvo);
    }

    /**
     *
     * @param input 表示界面输入栏中输入的字符串
     * @return 返回符合输入的账户列表
     */
    @Override
    public ArrayList<AccountVO> search(String input) {
        return account.search(input);
    }

    /**
     *
     * @param accountvo 表示一个Account在界面上显示的信息
     * @return 返回删除结果
     */
    @Override
    public ResultMessage delete(AccountVO accountvo) {
        return account.delete(accountvo);
    }

    /**
     *
     * @param accountVO
     * @return 返回更新结果
     */
    @Override
    public ResultMessage update(AccountVO accountVO) {
        return account.update(accountVO) ;
    }
}
