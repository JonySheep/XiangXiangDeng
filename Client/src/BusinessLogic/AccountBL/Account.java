
package BusinessLogic.AccountBL;

import DataService.AccountDataService;
import PO.AccountPO;
import Rmi.RemoteHelper;
import Util.ResultMessage;
import VO.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Account {

    private AccountPO VO2PO(AccountVO accountVO) {
        return  new AccountPO(accountVO.getCardNumber(),accountVO.getCardName(),accountVO.getBalance(),accountVO.getNotes());
    }


    private static AccountDataService accountDataService = RemoteHelper.getInstance().getAccountDataService();
    /**
     *
     * @param cardNumber id表示账号
     * @return 返回id对应账户信息
     */
    public AccountVO getInfo(String cardNumber) {
        try {
            return new AccountVO(accountDataService.getInfo(cardNumber));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param amount 表示账户余额
     * @return 返回更新结果
     */
    public ResultMessage updateBalance(double amount,String cardNumber) {
        AccountVO accountVO = getInfo(cardNumber);
        if (accountVO.getBalance() < amount){
            return ResultMessage.FAIL;
        }
        else{
            accountVO.setBalance(accountVO.getBalance()+amount);
            try {
                return accountDataService.update(VO2PO(accountVO));
            } catch (RemoteException e) {
                e.printStackTrace();
                return ResultMessage.ERROR;
            }
        }


    }

    /**
     *
     * @return 返回数据库中所有账户组成的列表
     */
    public ArrayList<AccountVO> getAccountList() {
        try {
            return getAccountVOList(accountDataService.getAllAccounts());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param accountvo 表示一个AccountVO对象
     * @return 返回新增客户的结果
     */
    public ResultMessage newAccount(AccountVO accountvo) {
        try {
            return accountDataService.insert(VO2PO(accountvo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     *
     * @param input 表示界面输入栏输入的字符
     * @return 返回符合条件的账户列表
     */
    public ArrayList<AccountVO> search(String input) {
        try {
            return getAccountVOList(accountDataService.search(input));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param accountvo 表示一个AccountVO对象
     * @return 返回删除结果
     */
    public ResultMessage delete(AccountVO accountvo) {
        try {
            return accountDataService.disguiseDeleteAccount(accountvo.getCardNumber());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     *
     * @param accountVO
     * @return 返回更新结果
     */
    public ResultMessage update(AccountVO accountVO) {
        try {
            return accountDataService.update(VO2PO(accountVO));
        } catch (RemoteException e) {

            return ResultMessage.ERROR;
        }
    }

    public ArrayList<AccountVO> getAccountVOList(ArrayList<AccountPO> accountPOS){
        int len = accountPOS.size();
        ArrayList<AccountVO> list = new ArrayList<>();
        for (int i=0;i<len;i++){
            AccountVO accountVO = new AccountVO(accountPOS.get(i));
            list.add(accountVO);
        }
        return list;

    }
}

