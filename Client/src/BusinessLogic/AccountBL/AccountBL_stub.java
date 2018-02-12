package BusinessLogic.AccountBL;

import BusinessLogicService.AccountBLService;
import Util.ResultMessage;
import VO.AccountVO;

import java.util.ArrayList;

;

/**
 * Created by Jeven on 2017/10/23.
 */
public class AccountBL_stub implements AccountBLService,AccountInfo {


    @Override
    public AccountVO getInfo(String id) {
        return null;
    }

    @Override
    public ResultMessage updateBalance(double amount, String carNumber) {
        return null;
    }

    @Override
    public ArrayList<AccountVO> getAccountList() {
        return null;
    }

    @Override
    public ArrayList<AccountVO> search(String input) {
        return null;
    }

    @Override
    public ResultMessage update(AccountVO accountVO) {
        return null;
    }

    @Override
    public ResultMessage delete(AccountVO accountvo) {
        return null;
    }

    @Override
    public ResultMessage newAccount(AccountVO accountvo) {
        return null;
    }
}
