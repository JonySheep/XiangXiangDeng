package Presentation.AccountUI;

import BusinessLogic.AccountBL.AccountController;
import BusinessLogicService.AccountBLService;
import Presentation.AccountUI.model.Account;
import Util.EmptyValue;
import Util.ResultMessage;
import VO.AccountVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Stack;

public class AccountDataHelper {

    //Do some change

    private static AccountDataHelper ourInstance = new AccountDataHelper();

    public static AccountDataHelper getInstance() {
        return ourInstance;
    }


    private ObservableList<Account> accountList = FXCollections.observableArrayList();
    private ObservableList<Account> originalAccountList = null;
    private AccountBLService accountBLService = new AccountController();

    private AccountDataHelper() {


        for(AccountVO vo:accountBLService.getAccountList()){
            accountList.add(new Account(
                    vo.getCardNumber(),
                    vo.getCardName(),
                    vo.getBalance(),
                    vo.getNotes())
            );
        }
        //仅供测试
//        accountList.add(new Account("6214667201888893","吃火锅的",200000.0,"大家吃火锅从这里扣钱"));
//        accountList.add(new Account("6214667201888894","买灯灯的",300000.0,"大家进货从这里扣钱"));
//        accountList.add(new Account("6214667201888895","发工资的",500000.0,"大家拿工资从这里扣钱"));
        originalAccountList = accountList;
    }

    public ObservableList<Account> getAccountList() {
        return accountList;
    }

    /**
     * 增加银行账户
     * @param cardNumber
     * @param cardName
     * @param balance
     * @param remark
     */
    public ResultMessage addAccount(String cardNumber, String cardName, Double balance, String remark){
        /**
         * 但其实洗数据应该放在BL
         */
        //检查是否有重复的银行卡号
        for(Account e: accountList){
            if(cardNumber == e.getCardNumber()){
                System.out.println("有重复");
                return ResultMessage.FAIL;
            }
        }

        //检查输入的余额是否大于零
        if(balance<0){
            System.out.println("余额小于零");
            return ResultMessage.FAIL;
        }

        if(cardNumber!= "" && cardName!="" && balance!=0.0 && remark!=""){
            ResultMessage rm = accountBLService.newAccount(new AccountVO(cardNumber,cardName,balance,remark));
            if(rm == ResultMessage.SUCCESS){
                accountList.add(new Account(cardNumber,cardName,balance,remark));
                return ResultMessage.SUCCESS;
            }
        }

        return ResultMessage.FAIL;

    }

    /**
     * 删除银行账户
     * @param list
     */
    public ResultMessage delAccount(ObservableList<Integer> list){
        ResultMessage rm = null;
        for(Integer e: list){
            Account account =  accountList.get(e);
            rm = accountBLService.delete(new AccountVO(
                    account.getCardNumber(),
                    account.getCardName(),
                    account.getBalance(),
                    account.getRemark()
            ));
        }

        if(rm ==ResultMessage.SUCCESS){
            for(Integer e: list){
                accountList.remove(e,e+1);
            }
            return ResultMessage.SUCCESS;
        }else return ResultMessage.FAIL;

    }

    /**
     * 修改银行账户
     * @param account
     * @return
     */
    public ResultMessage updateAccount(Account account){
        ResultMessage rm = accountBLService.update(new AccountVO(
                account.getCardNumber(),
                account.getCardName(),
                account.getBalance(),
                account.getRemark()
        ));

        if(rm == ResultMessage.SUCCESS){
            for(Account e: accountList){
                if(e.getCardNumber() == account.getCardNumber()) {
                    if(account.getCardName() != EmptyValue.getString()) {
                        e.setCardName(account.getCardName());
                    }
                    if(account.getRemark() != EmptyValue.getString()) {
                        e.setRemark(account.getRemark());
                    }
                }
            }
            return ResultMessage.SUCCESS;
        }else return ResultMessage.FAIL;


    }

    /**
     * 查询银行账户
     */
    public ResultMessage searchAccount(String str){
        ArrayList<AccountVO> l =  accountBLService.search(str);
        ObservableList<Account> list = FXCollections.observableArrayList();
        for(AccountVO vo:l){
            list.add(new Account(vo));
        }

        if(originalAccountList == null){//这样的话，多次搜索也好使
            originalAccountList = accountList;
        }
        accountList = list;
        return ResultMessage.SUCCESS;
    }

    /**
     * 返回查询前状态
     */
    public ResultMessage searchBack(){
        accountList = originalAccountList;
        return ResultMessage.SUCCESS;
    }

    /**
     * 同步银行账户列表
     */
    public ResultMessage syncAccountList(){
        /*
         * 链接BL
         */
        ArrayList<AccountVO> l =  accountBLService.getAccountList();
        ObservableList<Account> list = FXCollections.observableArrayList();
        for(AccountVO vo:l){
            list.add(new Account(vo));
        }

//        list.add(new Account("6214667201888896","买耳机的",500000.0,"大家买耳机从这里扣钱"));
//        list.add(new Account("6214667201888897","买卷子的",600000.0,"大家买卷子从这里扣钱"));
//        list.add(new Account("6214667201888896","买外卖的",700000.0,"大家买外卖从这里扣钱"));
        accountList = list;
        originalAccountList = null;
        return ResultMessage.SUCCESS;
    }
}

