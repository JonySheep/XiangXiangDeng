package Data;

import DataService.AccountDataService;
import DataUtil.DBHelper;
import DataUtil.DBUtil;
import PO.AccountPO;
import PO.UserPO;
import Util.EmptyValue;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDataImpl extends DBHelper implements AccountDataService {

    private static final String tableName = "accounttable";
    private static final String primaryKey = "AccountNumber";
    private static final int parameterNumber = 5;

    public AccountDataImpl(){
        super(tableName,primaryKey,parameterNumber);
    }

    public ResultMessage delete(AccountPO po) throws RemoteException {
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(getDeleteSQL());
            stat.setString(1,po.getCardNumber());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
        AccountPO temp = getInfo(po.getCardNumber());
        if (temp != null){
            return ResultMessage.ALREADYEXISTED;
        }
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(getInsertSQL());
            stat.setString(1,po.getCardName());
            stat.setString(2,po.getCardNumber());
            stat.setDouble(3,po.getBalance());
            stat.setString(4,po.getNotes());
            stat.setBoolean(5,false);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }


    /**
     *
     * 逐一检查po的各个参数，跟新所有不是empty的参数
     * @param po
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
        getConnection();
        AccountPO userPO = getInfo(po.getCardNumber());
        if (po.getCardName().equals(EmptyValue.getString())){
            po.setCardName(userPO.getCardName());
        }
        if (po.getBalance() == EmptyValue.getDouble()){
            po.setBalance(userPO.getBalance());
        }
        if (po.getNotes().equals(EmptyValue.getString())){
            po.setNotes(userPO.getNotes());
        }
        getConnection();
        ResultMessage temp1 = delete(po);
        getConnection();
        ResultMessage temp2 = insert(po);
        if (temp1 == ResultMessage.SUCCESS && temp2 == ResultMessage.SUCCESS){
            return ResultMessage.SUCCESS;
        }
        else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public AccountPO getInfo(String cardNumber) throws RemoteException {
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(getSelectSQL());
            stat.setString(1,cardNumber);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                String AName = rs.getString(1);
                String ANumber = rs.getString(2);
                double AB = rs.getDouble(3);
                String ANote = rs.getString(4);
                AccountPO accountPO = new AccountPO(ANumber,AName,AB,ANote);
                return accountPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<AccountPO> search(String str) throws RemoteException {
        String sql1 = "select * from accounttable where AccountName like '%" + str + "%' and isdelete = false;";
        ArrayList<AccountPO> list1 = makeAccountList(sql1);
        String sql2 = "select * from accounttable where AccountNumber like '%" + str + "%'and isdelete = false;";
        ArrayList<AccountPO> list2 = makeAccountList(sql2);
        int len = list2.size();
        for (int i=0;i<len;i++){
            AccountPO temp = list2.get(i);
            list1.add(temp);
        }
        return list1;
    }

    @Override
    public ArrayList<AccountPO> getAllAccounts() {
       String sql = "select * from accounttable;";
       return makeAccountList(sql);
    }

    private void getConnection(){
        if (conn == null){
            conn = DBUtil.open();
        }
    }


    private ArrayList<AccountPO> makeAccountList(String sql){
        getConnection();
        ArrayList<AccountPO> list = new ArrayList<AccountPO>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String AName = rs.getString(1);
                String ANumber = rs.getString(2);
                double AB = rs.getDouble(3);
                String ANote = rs.getString(4);
                AccountPO accountPO = new AccountPO(ANumber,AName,AB,ANote);
                list.add(accountPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ResultMessage disguiseDeleteAccount(String number){
        getConnection();
        String sql = "update " + tableName + " set isDelete = true where "+ primaryKey + " = " + number+ ";";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }
}
