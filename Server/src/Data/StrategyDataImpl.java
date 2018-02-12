package Data;

import DataService.StrategyDataService;
import DataUtil.DBHelper;
import DataUtil.DBUtil;
import PO.*;
import Util.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class StrategyDataImpl implements StrategyDataService{

    //数据库连接
    private Connection conn;

    DBHelper customerStrategy;
    DBHelper packageStrategy;
    DBHelper amountStrategy;


    //为customerStrategy设置参数
    private static final String tablename1 = "customerstrategytable";
    private static final String primaryKey1 = "cusID";
    private static final int parameterNumber1 = 7;

    //为packageStrategy设置参数
    private static final String tablename2 = "packagestrategytable";
    private static final String primaryKey2 = "packageID";
    private static final int parameterNumber2 = 6;

    //为amountStrategy设置参数
    private static final String tablename3 = "amountstrategytable";
    private static final String primaryKey3 = "amountID";
    private static final int parameterNumber3 = 6;

    //重载构造函数
    public StrategyDataImpl(){
        customerStrategy = new DBHelper(tablename1,primaryKey1,parameterNumber1);
        packageStrategy = new DBHelper(tablename2,primaryKey2,parameterNumber2);
        amountStrategy = new DBHelper(tablename3,primaryKey3,parameterNumber3);
        getConnection();
    }

    @Override
    public ResultMessage insertCusStrategy(CustomerStrategyPO cusstrategypo) throws RemoteException {
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(customerStrategy.getInsertSQL());
            stat.setNull(1, Types.INTEGER);
            stat.setDate(2, Date.valueOf(cusstrategypo.getBegin()));
            stat.setDate(3, Date.valueOf(cusstrategypo.getEnd()));
            stat.setInt(4,cusstrategypo.getLevel());
            stat.setString(5,getDBgift(cusstrategypo.getGift()));
            stat.setInt(6,cusstrategypo.getVouchers().getAmount());
            stat.setDouble(7,cusstrategypo.getDiscount());
            stat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage insertPkgStrategy(PackageStrategyPO pkgstrategypo) throws RemoteException {
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(packageStrategy.getInsertSQL());
            stat.setNull(1,Types.INTEGER);
            stat.setDate(2, Date.valueOf(pkgstrategypo.getBegin()));
            stat.setDate(3, Date.valueOf(pkgstrategypo.getEnd()));
            stat.setString(4,pkgstrategypo.getPkgName());
            stat.setDouble(5,pkgstrategypo.getPrice());
            stat.setString(6,getDBpackage(pkgstrategypo.getPkgGood()));
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage insertAmouStrategy(AmountStrategyPO amoustrategypo) throws RemoteException {
      getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(amountStrategy.getInsertSQL());
            stat.setNull(1,Types.INTEGER);
            stat.setDate(2, Date.valueOf(amoustrategypo.getBegin()));
            stat.setDate(3, Date.valueOf(amoustrategypo.getEnd()));
            stat.setDouble(4,amoustrategypo.getAmount());
            stat.setString(5,getDBgift(amoustrategypo.getGift()));
            stat.setInt(6,amoustrategypo.getVouchers().getAmount());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage delete(StrategyPO strategypo) throws RemoteException {
        String sql;
        if (strategypo.getType() == StrategyType.CUSTOMER) {
            sql = customerStrategy.getDeleteSQL();
        }
        else if(strategypo.getType() == StrategyType.PACKAGE){
            sql = packageStrategy.getDeleteSQL();
        }
        else{
            sql = amountStrategy.getDeleteSQL();
        }
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,getDBID(strategypo.getId()));
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public StrategyPO getStrategy(String id) throws RemoteException {
        String str = id.substring(0,1);
        if (str.equals("AS")){
           return findAmount(id);
        }
        else if (str.equals("CS")){
            return findCus(id);
        }
        else{
            return findPak(id);
        }
    }

    @Override
    public CustomerStrategyPO getCurrentCusStrategy(LocalDate date, int level) throws RemoteException {
        String sql = "select * from customerstrategytable where level = "+ level +" and DATE(beginDate) <= '" + date + "'" + "and DATE(endDate) >= +'" + date + "';";
        getConnection();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                int DBid = rs.getInt(1);
                LocalDate DBbegin = rs.getDate(2).toLocalDate();
                LocalDate DBend = rs.getDate(3).toLocalDate();
                int DBlevel = rs.getInt(4);
                String DBgift = rs.getString(5);
                int DBvoucher = rs.getInt(6);
                double DBdiscount = rs.getDouble(7);
                String id = getPOID(StrategyType.CUSTOMER,DBid);
                ArrayList<GiftItem> giftItems = getPOgift(DBgift);
                Voucher voucher = new Voucher(DBvoucher);
                CustomerStrategyPO customerStrategyPO = new CustomerStrategyPO(StrategyType.CUSTOMER,DBbegin,DBend,id,level,giftItems,voucher,DBdiscount);
                return customerStrategyPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return null;
    }

    @Override
    public ArrayList<PackageStrategyPO> getCurrentPkgStrategy(LocalDate date) throws RemoteException {
        String sql = "select * from packagestrategytable where DATE(beginDate) <= '" + date + "' and DATE(endDate) >= '" + date + "';";

        ArrayList<PackageStrategyPO> packageStrategyPOS = new ArrayList<>();
        getConnection();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String id = getPOID(StrategyType.PACKAGE,rs.getInt(1));
                LocalDate begin = rs.getDate(2).toLocalDate();
                LocalDate end = rs.getDate(3).toLocalDate();
                String name = rs.getString(4);
                double price = rs.getDouble(5);
                ArrayList<PackageGoodItem> packageGoodItems = getPoPackage(rs.getString(6));
                PackageStrategyPO packageStrategyPO = new PackageStrategyPO(StrategyType.PACKAGE,begin,end,id,name,price,packageGoodItems);
                packageStrategyPOS.add(packageStrategyPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageStrategyPOS;
    }

    @Override
    public AmountStrategyPO getCurrentAmouStrategy(LocalDate date, double amount) throws RemoteException {

        String sql = "select * from amountstrategytable where DATE(beginDate) <= '" + date + "' and DATE(endDate) >= " + date +" and amount <=  " + amount + ";";

        ArrayList<AmountStrategyPO> amountStrategyPOS = new ArrayList<>();
        getConnection();

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String id = getPOID(StrategyType.AMMOUNT,rs.getInt(1));
                LocalDate begin = rs.getDate(2).toLocalDate();
                LocalDate end = rs.getDate(3).toLocalDate();
                double DBamount = rs.getDouble(4);
                ArrayList<GiftItem> giftItems = getPOgift(rs.getString(5));
                Voucher voucher = new Voucher(rs.getInt(6));
                AmountStrategyPO amountStrategyPO = new AmountStrategyPO(StrategyType.AMMOUNT,begin,end,id,DBamount,giftItems,voucher);
                amountStrategyPOS.add(amountStrategyPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (amountStrategyPOS.size() == 0){
            return null;
        }
        else{
            AmountStrategyPO amountStrategyPO = amountStrategyPOS.get(0);
            int len = amountStrategyPOS.size();
            for (int i=0;i<len;i++){
                if (amountStrategyPOS.get(i).getAmount() > amountStrategyPO.getAmount()){
                    amountStrategyPO = amountStrategyPOS.get(i);
                }
            }
            return amountStrategyPO;
        }
    }

    @Override
    public ArrayList<CustomerStrategyPO> getCusStrategy() throws RemoteException {
        getConnection();
        String sql = "select * from customerstrategytable;";
        ArrayList<CustomerStrategyPO> customerStrategyPOS = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                int DBid = rs.getInt(1);
                LocalDate DBbegin = rs.getDate(2).toLocalDate();
                LocalDate DBend = rs.getDate(3).toLocalDate();
                int DBlevel = rs.getInt(4);
                String DBgift = rs.getString(5);
                int DBvoucher = rs.getInt(6);
                double DBdiscount = rs.getDouble(7);
                String id = getPOID(StrategyType.CUSTOMER,DBid);
                ArrayList<GiftItem> giftItems = getPOgift(DBgift);
                Voucher voucher = new Voucher(DBvoucher);
                CustomerStrategyPO customerStrategyPO = new CustomerStrategyPO(StrategyType.CUSTOMER,DBbegin,DBend,id,DBlevel,giftItems,voucher,DBdiscount);
                customerStrategyPOS.add(customerStrategyPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerStrategyPOS;


    }

    @Override
    public ArrayList<PackageStrategyPO> getPkgStrategy() throws RemoteException {
        String sql = "SELECT * from packagestrategytable; ";
        ArrayList<PackageStrategyPO> packageStrategyPOS = new ArrayList<>();
        getConnection();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String id = getPOID(StrategyType.PACKAGE,rs.getInt(1));
                LocalDate begin = rs.getDate(2).toLocalDate();
                LocalDate end = rs.getDate(3).toLocalDate();
                String name = rs.getString(4);
                double price = rs.getDouble(5);
                ArrayList<PackageGoodItem> packageGoodItems = getPoPackage(rs.getString(6));
                PackageStrategyPO packageStrategyPO = new PackageStrategyPO(StrategyType.PACKAGE,begin,end,id,name,price,packageGoodItems);
                packageStrategyPOS.add(packageStrategyPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageStrategyPOS;
    }

    @Override
    public ArrayList<AmountStrategyPO> getAmouStrategy() throws RemoteException {

        String sql = "SELECT * from amountstrategytable;";
        ArrayList<AmountStrategyPO> amountStrategyPOS = new ArrayList<>();
        getConnection();

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String id = getPOID(StrategyType.AMMOUNT,rs.getInt(1));
                LocalDate begin = rs.getDate(2).toLocalDate();
                LocalDate end = rs.getDate(3).toLocalDate();
                double DBamount = rs.getDouble(4);
                ArrayList<GiftItem> giftItems = getPOgift(rs.getString(5));
                Voucher voucher = new Voucher(rs.getInt(6));
                AmountStrategyPO amountStrategyPO = new AmountStrategyPO(StrategyType.AMMOUNT,begin,end,id,DBamount,giftItems,voucher);
                amountStrategyPOS.add(amountStrategyPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amountStrategyPOS;
    }

    @Override
    public ResultMessage updateCusStrategy(CustomerStrategyPO customerStrategyPO) throws RemoteException {
        delete(customerStrategyPO);
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(customerStrategy.getInsertSQL());
            stat.setInt(1, getDBID(customerStrategyPO.getId()));
            stat.setDate(2, Date.valueOf(customerStrategyPO.getBegin()));
            stat.setDate(3, Date.valueOf(customerStrategyPO.getEnd()));
            stat.setInt(4,customerStrategyPO.getLevel());
            stat.setString(5,getDBgift(customerStrategyPO.getGift()));
            stat.setInt(6,customerStrategyPO.getVouchers().getAmount());
            stat.setDouble(7,customerStrategyPO.getDiscount());
            stat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }

    }

    @Override
    public ResultMessage updatePkgStrategy(PackageStrategyPO packageStrategyPO) throws RemoteException {
        delete(packageStrategyPO);
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(packageStrategy.getInsertSQL());
            stat.setInt(1,getDBID(packageStrategyPO.getId()));
            stat.setDate(2, Date.valueOf(packageStrategyPO.getBegin()));
            stat.setDate(3, Date.valueOf(packageStrategyPO.getEnd()));
            stat.setString(4,packageStrategyPO.getPkgName());
            stat.setDouble(5,packageStrategyPO.getPrice());
            stat.setString(6,getDBpackage(packageStrategyPO.getPkgGood()));
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage updateAmouStrategy(AmountStrategyPO amountStrategyPO) throws RemoteException {
       delete(amountStrategyPO);
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(amountStrategy.getInsertSQL());
            stat.setInt(1,getDBID(amountStrategyPO.getId()));
            stat.setDate(2, Date.valueOf(amountStrategyPO.getBegin()));
            stat.setDate(3, Date.valueOf(amountStrategyPO.getEnd()));
            stat.setDouble(4,amountStrategyPO.getAmount());
            stat.setString(5,getDBgift(amountStrategyPO.getGift()));
            stat.setInt(6,amountStrategyPO.getVouchers().getAmount());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }

    }

    //检测数据库是否正常连接
    private void getConnection(){
        if (conn == null){
            conn = DBUtil.open();
        }
    }

    //String类型ID转数据库内保存的int类型ID
    private int getDBID(String id){
        String str = id.substring(2);
        int value = Integer.valueOf(str);
        return value;
    }

    //int类型ID转String类型PO专用id
    private String getPOID(StrategyType strategyType,int id){
        String str = String.valueOf(id);
        int len = str.length();
        while(len < 6){
            str = "0" + str;
            len++;
        }
        if(strategyType == StrategyType.CUSTOMER){
            return "CS"+str;
        }
        else if(strategyType == StrategyType.PACKAGE){
            return "PS"+str;
        }
        else{
            return "AS"+str;
        }

    }

    //将GiftItem按id , name , number ,形式排列成字符串
    private String getDBgift(ArrayList<GiftItem> gift){
        int len = gift.size();
        String str = "";
        for (int i=0;i<len;i++){
            GiftItem temp = gift.get(i);
           if (i == len-1){
               str = str + temp.getGoodID() + "," + temp.getGoodName() + "," + temp.getNumber();
           }
           else{
               str = str + temp.getGoodID() + "," + temp.getGoodName() + "," + temp.getNumber() + ",";
           }
        }
        return str;
    }

    //将数据库中的字符串解析成ArrayList<GiftItem>
    private ArrayList<GiftItem> getPOgift(String DBgift){

        String[] array = DBgift.split(",");
        int len = array.length;
        ArrayList<GiftItem> list = new ArrayList<>();
        for (int i=0;i<len;i++){
            String id = array[i];
            i++;
            String name = array[i];
            i++;
            int number = Integer.valueOf(array[i]);
            GiftItem giftItemPO = new GiftItem(name,id,number);
            list.add(giftItemPO);
        }
        return list;
    }


    //将packageItem按ID，number存进数据库
    private String getDBpackage(ArrayList<PackageGoodItem> packageGoodItems){
        int len = packageGoodItems.size();
        String str = "";
        for (int i=0;i<len;i++){
            PackageGoodItem packageGoodItem = packageGoodItems.get(i);

            if(i == len-1){
                str = str +  packageGoodItem.getGoodID() + "," + packageGoodItem.getGoodNumber();
            }
            else{
                str = str +  packageGoodItem.getGoodID() + "," + packageGoodItem.getGoodNumber() + ",";
            }
        }
        return str;
    }

    //将数据库存的package解析成PackageItem
    private ArrayList<PackageGoodItem> getPoPackage(String DBpka){
        String[] array = DBpka.split(",");
        ArrayList<PackageGoodItem> packageGoodItems = new ArrayList<>();
        int len = array.length;
        for (int i=0;i<len;i++) {
            String id = array[i];
            i++;
            int number = Integer.valueOf(array[i]);
            PackageGoodItem packageGoodItem = new PackageGoodItem(id,number);
            packageGoodItems.add(packageGoodItem);
        }
        return packageGoodItems;
    }

    private CustomerStrategyPO findCus(String id){
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(customerStrategy.getSelectSQL());
            stat.setInt(1,getDBID(id));
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                int DBid = rs.getInt(1);
                LocalDate DBbegin = rs.getDate(2).toLocalDate();
                LocalDate DBend = rs.getDate(3).toLocalDate();
                int DBlevel = rs.getInt(4);
                String DBgift = rs.getString(5);
                int DBvoucher = rs.getInt(6);
                double DBdiscount = rs.getDouble(7);
                String POid = getPOID(StrategyType.CUSTOMER,DBid);
                ArrayList<GiftItem> giftItems = getPOgift(DBgift);
                Voucher voucher = new Voucher(DBvoucher);
                CustomerStrategyPO customerStrategyPO = new CustomerStrategyPO(StrategyType.CUSTOMER,DBbegin,DBend,id,DBlevel,giftItems,voucher,DBdiscount);
                return customerStrategyPO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PackageStrategyPO findPak(String id){
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(packageStrategy.getSelectSQL());
            stat.setInt(1,getDBID(id));
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                String DBid = getPOID(StrategyType.PACKAGE,rs.getInt(1));
                LocalDate begin = rs.getDate(2).toLocalDate();
                LocalDate end = rs.getDate(3).toLocalDate();
                String name = rs.getString(4);
                double price = rs.getDouble(5);
                ArrayList<PackageGoodItem> packageGoodItems = getPoPackage(rs.getString(6));
                PackageStrategyPO packageStrategyPO = new PackageStrategyPO(StrategyType.PACKAGE,begin,end,id,name,price,packageGoodItems);
                return  packageStrategyPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AmountStrategyPO findAmount(String id){
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(amountStrategy.getSelectSQL());
            stat.setInt(1,getDBID(id));
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                String DBid = getPOID(StrategyType.AMMOUNT,rs.getInt(1));
                LocalDate begin = rs.getDate(2).toLocalDate();
                LocalDate end = rs.getDate(3).toLocalDate();
                double DBamount = rs.getDouble(4);
                ArrayList<GiftItem> giftItems = getPOgift(rs.getString(5));
                Voucher voucher = new Voucher(rs.getInt(6));
                AmountStrategyPO amountStrategyPO = new AmountStrategyPO(StrategyType.AMMOUNT,begin,end,id,DBamount,giftItems,voucher);
                return amountStrategyPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
