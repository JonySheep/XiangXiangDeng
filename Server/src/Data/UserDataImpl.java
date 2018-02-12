package Data;

import DataService.UserDataService;
import DataUtil.DBHelper;
import DataUtil.DBUtil;
import PO.UserPO;
import Util.EmptyValue;
import Util.ResultMessage;
import Util.UserLevel;
import Util.UserRole;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class UserDataImpl  extends DBHelper implements UserDataService {

    private static final String tableName = "UserTable";
    private static final String primaryKey = "userID";
    private static final int parameterNumber = 7;

    //重载构造函数
    public UserDataImpl(){
        super(tableName,primaryKey,parameterNumber);
    }

    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        if (conn == null){
            conn = DBUtil.open();
        }
        try {
            PreparedStatement stat = conn.prepareStatement(getInsertSQL());
            stat.setNull(1,Types.INTEGER);
            stat.setString(2,po.getPassword());
            stat.setString(3,po.getName());
            stat.setString(4,po.getLevel().toString());
            stat.setString(5,po.getRole().toString());
            stat.setBoolean(6,false);
            stat.setBoolean(7,false);
            stat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    //真删除
    private ResultMessage delete(String id) throws RemoteException {
        if (conn == null){
            conn = DBUtil.open();
        }
        try {
            PreparedStatement stat = conn.prepareStatement(getDeleteSQL());
            stat.setInt(1,Integer.valueOf(id));
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }

    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        if (conn == null){
            conn = DBUtil.open();
        }
        String UID = po.getUserID();
        UserPO DBpo = find(UID);
        String tempName = po.getName();
        UserLevel tempLevel = po.getLevel();
        UserRole tempRole = po.getRole();
        if (po.getName().equals(EmptyValue.getString())){
            tempName = DBpo.getName();
        }
        if (po.getLevel() == UserLevel.EMPTYLEVEL){
            tempLevel = DBpo.getLevel();
        }
        if (po.getRole() == UserRole.EMPTYROLE){
            tempRole = DBpo.getRole();
        }
        try {
            this.delete(po.getUserID());
            conn = DBUtil.open();
            PreparedStatement stat = conn.prepareStatement(getInsertSQL());
            stat.setInt(1,Integer.valueOf(po.getUserID()));
            stat.setString(2,po.getPassword());
            stat.setString(3,tempName);
            stat.setString(4,tempLevel.toString());
            stat.setString(5,tempRole.toString());
            stat.setBoolean(6,false);
            stat.setBoolean(7,false);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public UserPO find(String id) throws RemoteException {
        if (conn == null){
            conn = DBUtil.open();
        }
        try {
            PreparedStatement stat = conn.prepareStatement(getSelectSQL());
            stat.setInt(1,Integer.valueOf(id));
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                int valueOfId = rs.getInt(1);
                String upassword = rs.getString(2);
                String uname = rs.getString(3);
                String ulevel = rs.getString(4);
                String urole = rs.getString(5);
                UserPO userPO = new UserPO(makeId(valueOfId),upassword,uname,makeLevel(ulevel),makeRole(urole));
                return userPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("对应ID不存在");
        }
        return null;
    }

    @Override
    public ResultMessage login(String id, String password) throws RemoteException {
        if (conn == null){
            conn = DBUtil.open();
        }
        boolean login = false;
        try {
            PreparedStatement stat = conn.prepareStatement(getSelectSQL());
            stat.setInt(1,Integer.valueOf(id));
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                String tempPassword = rs.getString(2);
                boolean isLogin = rs.getBoolean(6);
                if (isLogin == true && tempPassword.equals(password)){
                    return ResultMessage.REPEATEDLOGIN;
                }
                else{
                    if (! tempPassword.equals(password)){
                        return ResultMessage.FAILED;
                    }
                    else{
                        login = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (login){
            try {
                String sql = "update userTable set loginState = ? where userID = ?;";
                PreparedStatement pstat = conn.prepareCall(sql);
                pstat.setBoolean(1,true);
                pstat.setInt(2,Integer.valueOf(id));
                pstat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                return ResultMessage.SUCCESS;
            }

        }
        return ResultMessage.FAILED;
    }

    @Override
    public boolean logout(String id) throws RemoteException {
        if (conn == null){
            conn = DBUtil.open();
        }
        String sql = "update userTable set loginState = ? where userID = ?;";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setBoolean(1,false);
            stat.setInt(2,Integer.valueOf(id));
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            return true;
        }
    }

    @Override
    public boolean resetPassword(String id, String oldPassword, String newPassword) throws RemoteException {
        ResultMessage temp = login(id,oldPassword);
        if (temp == ResultMessage.SUCCESS || temp == ResultMessage.REPEATEDLOGIN)
        {
            if (conn == null){
                conn = DBUtil.open();
            }
            String sql = "update userTable set password = ? where userID = ?;";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1,newPassword);
                stat.setInt(2,Integer.valueOf(id));
                stat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (temp == ResultMessage.SUCCESS){
                    logout(id);
                }
                return true;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<UserPO> getAllUser() throws RemoteException {
        String sql = "select * from UserTable where isDeleted = false;";
        return makeUserList(sql);

    }
    @Override
    public ArrayList<UserPO> findUsers(String str) throws RemoteException {
        String sql1 = "select * from UserTable where name like '%" + str + "%' and isDeleted = false;";
        String sql2 = "select * from UserTable where userID like '%" + str + "%' and isDeleted = false;";
        ArrayList<UserPO> userPOS1 = makeUserList(sql1);
        ArrayList<UserPO> userPOS2 = makeUserList(sql2);
        int len2 = userPOS2.size();
        for (int i=0;i<len2;i++){
            UserPO temp = userPOS2.get(i);
            if ( !userPOS1.contains(temp)){
                userPOS1.add(temp);
            }
        }
        return userPOS1;
    }


    @Override
    public ResultMessage disguiseDelete(String id){
        if (conn == null){
            DBUtil.open();
        }
        String sql = "UPDATE usertable SET isDeleted = TRUE WHERE userID = "+ Integer.valueOf(id)+ " ;";
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

    private String makeId(int id){
        String temp = String.valueOf(id);
        int len = temp.length();
        if (len < 8)
        {
            while(len < 8)
            {
                temp = "0" + temp;
                len++;
            }
        }
        return temp;
    }

    private UserLevel makeLevel(String level){
        switch (level){
            case "GENERAL":
                return UserLevel.GENERAL;
            case "FINALLEVEL":
                return UserLevel.FINALLEVEL;
            default:
                return UserLevel.EMPTYLEVEL;
        }
    }


    private UserRole makeRole(String role){
        switch (role){
            case "INVENTORY":
                return UserRole.INVENTORY;
            case "IMPORT":
                return UserRole.IMPORT;
            case "SALES":
                return UserRole.SALES;
            case "SALESMANAGER":
                return UserRole.SALESMANAGER;
            case "GENERALMANAGER":
                return UserRole.GENERALMANAGER;
            case "FINANCIAL":
                return UserRole.FINANCIAL;
            case "ADMIN":
                return UserRole.ADMIN;
            default:
                return UserRole.EMPTYROLE;
        }
    }

    private ArrayList<UserPO> makeUserList(String sql) {
        if (conn == null)
        {
            conn = DBUtil.open();
        }
        ArrayList<UserPO> list = new ArrayList<UserPO>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String UID = makeId(rs.getInt(1));
                String UPW = rs.getString(2);
                String UNM = rs.getString(3);
                UserLevel UL = makeLevel(rs.getString(4));
                UserRole UR = makeRole(rs.getString(5));
                UserPO userPO = new UserPO(UID,UPW,UNM,UL,UR);
                list.add(userPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return list;
    }
}
