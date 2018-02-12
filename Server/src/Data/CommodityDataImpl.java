package Data;

import DataService.CommodityDataService;
import DataUtil.DBHelper;
import DataUtil.DBUtil;
import PO.*;
import Util.EmptyValue;
import Util.GoodTreeNodeType;
import Util.ResultMessage;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.rmi.RemoteException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CommodityDataImpl implements CommodityDataService {

    private Connection conn = null;

    //用组合实现接口分离
    DBHelper good;
    DBHelper category;
    DBHelper commodity;

    //为good设置参数
    private static final String tablename1 = "goodtable";
    private static final String primaryKey1 = "goodID";
    private static final int parameterNumber1 = 12;

    //为category设置参数
    private static final String tablename2 = "categorytable";
    private static final String primaryKey2 = "categoryID";
    private static final int parameterNumber2 = 5;

    //为commodity设置参数
    private static final String tablename3 = "commoditytable";
    private static final String primaryKey3 = "goodid";
    private static final int parameterNumber3 = 4;


    public CommodityDataImpl(){
        good = new DBHelper(tablename1,primaryKey1,parameterNumber1);
        category = new DBHelper(tablename2,primaryKey2,parameterNumber2);
        commodity = new DBHelper(tablename3,primaryKey3,parameterNumber3);
        getConnection();
    }

    private PreparedStatement fillCategoryInsert(PreparedStatement stat,CategoryPO categoryPO) throws SQLException {
        stat.setNull(1, Types.INTEGER);
        stat.setString(2,categoryPO.getName());
        stat.setString(3,categoryPO.getFatherCategoryId());
        stat.setString(4,"");
        stat.setInt(5,0);
        return stat;
    }

    @Override
    public GoodTreePO getGoodTree() throws RemoteException {

        ArrayList<GoodTreeNodePO> goodTreeNodePOS = new ArrayList<>();
        ArrayList<CategoryPO> categoryPOS = getcategoryPOS();
        ArrayList<GoodPO> goodPOS = getgoodPOS();
        int len1 = categoryPOS.size();
        int len2 = goodPOS.size();
        for (int i=0;i<len1;i++){
            CategoryPO temp = categoryPOS.get(i);
            goodTreeNodePOS.add(temp);
        }
        for (int i=0;i<len2;i++){
            GoodPO temp = goodPOS.get(i);
            goodTreeNodePOS.add(temp);
        }

        for (int i=0;i<len1+len2;i++){
            GoodTreeNodePO temp1 = goodTreeNodePOS.get(i);
            String temp1ID;
            if (temp1.getNodeType() == GoodTreeNodeType.GOOD){
                temp1ID = ((GoodPO) temp1).getId();
            }
            else{
                temp1ID = ((CategoryPO) temp1).getId();
            }
            for (int j=0;j<len1+len2;j++){
                GoodTreeNodePO temp2 = goodTreeNodePOS.get(j);
                String fatherID;
                if (temp2.getNodeType() == GoodTreeNodeType.GOOD){
                    fatherID = ((GoodPO) temp2).getFatherCategoryId();
                }
                else{
                    fatherID = ((CategoryPO) temp2).getFatherCategoryId();
                }
                if (temp1ID.equals(fatherID)){
                    goodTreeNodePOS.get(j).setFatherIndex(i);
                    ArrayList<Integer> list = goodTreeNodePOS.get(i).getSonIndexes();
                    list.add(j);
                    goodTreeNodePOS.get(i).setSonIndexes(list);
                }

            }

        }
        GoodTreePO goodTreePO = new GoodTreePO(goodTreeNodePOS);
        return goodTreePO;
    }

    @Override
    public ResultMessage addCategory(CategoryPO newCategoryPO) throws RemoteException {
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(category.getInsertSQL());
            stat = fillCategoryInsert(stat,newCategoryPO);
            stat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    //目录删除为真删除
    @Override
    public ResultMessage delCategory(String delCategoryID) throws RemoteException {
        getConnection();
        String sql = "delete from " + tablename2 + " where " + primaryKey2 + " = " + getCategoryDBID(delCategoryID) + ";";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
//            String sql1 = "truncate table categorytable;";
//            try {
//                Statement stat = conn.createStatement();
//                stat.executeUpdate(sql1);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            return ResultMessage.SUCCESS;
        }
    }


    //仅更新categorytable中的父节点编号和自身名字
    @Override
    public ResultMessage updateCategory(CategoryPO categoryPO) throws RemoteException {
        if ( !categoryPO.getName().equals(EmptyValue.getString())){
            getConnection();

            String sql = "update " + tablename2 + " set categoryName =  '" + categoryPO.getName() + " ' where "+ primaryKey2 + " = " + getCategoryDBID(categoryPO.getId()) + ";";

            try {
               Statement stat = conn.createStatement();
                stat.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                return ResultMessage.FAILED;
            }
        }
        if ( !categoryPO.getFatherCategoryId().equals(EmptyValue.getString())){
            String sql = "update " + tablename2 + " set fatherCategoryID = '" + categoryPO.getFatherCategoryId() + "' where "+ primaryKey2 + " = " + getCategoryDBID(categoryPO.getId()) + ";";

            try {
                Statement stat = conn.createStatement();

                stat.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                return ResultMessage.FAILED;
            }
        }
        return ResultMessage.SUCCESS;
    }


    // 填充good的insertSql语句
    private PreparedStatement fillGoodInsert(PreparedStatement stat,GoodPO goodPO) throws SQLException {
        stat.setString(1,goodPO.getId());
        stat.setString(2,goodPO.getName());
        stat.setString(3,goodPO.getFatherCategoryId());
        stat.setString(4,goodPO.getType());
        stat.setInt(5,goodPO.getWarningAmount());
        stat.setDouble(6,goodPO.getBuyingPrice());
        stat.setDouble(7, goodPO.getSellingPrice());
        stat.setInt(8,goodPO.getNowAmount());
        stat.setDouble(9,goodPO.getRecentBuyingPrice());
        stat.setDouble(10,goodPO.getRecentSellingPrice());
        stat.setBoolean(11,false);
        stat.setString(12,goodPO.getComment());
        return stat;
    }
    @Override
    public ResultMessage addGood(GoodPO newGoodPO) throws RemoteException {
        DBCategory dbCategory = searchCategory(newGoodPO.getFatherCategoryId());
        int sonNum = dbCategory.getSonNumber() + 1;
        String str = String.valueOf(sonNum);
        int len = str.length();
        while(len < 4){
            str = "0" + str;
            len++;
        }
        String goodId = newGoodPO.getFatherCategoryId()+ "-"+ str;
        String sonId = dbCategory.getSonID();
        sonId = sonId + " " + goodId;
        dbCategory.setSonID(sonId);
        dbCategory.setSonNumber(sonNum);

        //更新目录字段
        ResultMessage updateResult = updatePartOfCategory(dbCategory);
        if (updateResult == ResultMessage.FAILED){
            return ResultMessage.FAILED;
        }
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(good.getInsertSQL());
            stat = fillGoodInsert(stat,newGoodPO);
            stat.setString(1,goodId);
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
     * 伪删除，跟新数据库中的删除状态
     * @param delGoodID 要删除的商品的id
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage delGood(String delGoodID) throws RemoteException {
        getConnection();
        String sql = "update " + tablename1 + " set isDeleted = true where "+ primaryKey1 + " = '" + delGoodID + "';";
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

    @Override
    public ResultMessage updateGood(GoodPO goodPO) throws RemoteException {
        DBGood dbGood = searchGoodByID(goodPO.getId());
        if (goodPO.getName().equals(EmptyValue.getString())){
            goodPO.setName(dbGood.getGoodPO().getName());
        }
        if (goodPO.getFatherCategoryId().equals(EmptyValue.getString())){
            goodPO.setFatherCategoryId(dbGood.getGoodPO().getFatherCategoryId());
        }
        if (goodPO.getType().equals(EmptyValue.getString())){
            goodPO.setType(dbGood.getGoodPO().getType());
        }
        if (goodPO.getWarningAmount().equals(EmptyValue.getInteger())){
            goodPO.setWarningAmount(dbGood.getGoodPO().getWarningAmount());
        }
        if (goodPO.getBuyingPrice().equals(EmptyValue.getDouble())){
            goodPO.setBuyingPrice(dbGood.getGoodPO().getBuyingPrice());
        }
        if (goodPO.getSellingPrice().equals(EmptyValue.getDouble())){
            goodPO.setSellingPrice(dbGood.getGoodPO().getSellingPrice());
        }
        if (goodPO.getNowAmount().equals(EmptyValue.getInteger())){
            goodPO.setNowAmount(dbGood.getGoodPO().getNowAmount());
        }
        if (goodPO.getRecentBuyingPrice().equals(EmptyValue.getDouble())){
            goodPO.setRecentBuyingPrice(dbGood.getGoodPO().getRecentBuyingPrice());
        }
        if (goodPO.getRecentSellingPrice().equals(EmptyValue.getDouble())){
            goodPO.setRecentSellingPrice(dbGood.getGoodPO().getRecentSellingPrice());
        }
        if (goodPO.getComment().equals(EmptyValue.getString())){
            goodPO.setComment(dbGood.getGoodPO().getComment());
        }
        ResultMessage delResult = delGoodInDB(goodPO.getId());
        if (delResult == ResultMessage.FAILED){
            return ResultMessage.FAILED;
        }
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(good.getInsertSQL());
            stat = fillGoodInsert(stat,goodPO);
            stat.setString(1,goodPO.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public GoodPO goodsearchByID(String id){
        DBGood temp = searchGoodByID(id);

        return temp.getGoodPO();

    }

    @Override
    public ArrayList<GoodPO> searchGood(String input, String CategoryID) throws RemoteException {
        String sql1 = "select * from goodtable where goodID like '%"+input+"%' and isDeleted = false;";
        String sql2 = "select * from goodtable where goodName like '%"+input+"%' and isDeleted = false;";

        ArrayList<GoodPO> list1 = randomSearch(sql1);
        ArrayList<GoodPO> list2 = randomSearch(sql2);

        int len2 = list2.size();
        for (int i=0;i<len2;i++){

            GoodPO temp = list2.get(i);
            if (list1.contains(temp)){
                continue;
            }
            else{
                list1.add(list2.get(i));
            }
        }

        ArrayList<GoodPO> list3 = new ArrayList<>();
        int len1 = list1.size();
        for (int i=0;i<len1;i++){
            GoodPO temp = list1.get(i);
            String fatherID = temp.getFatherCategoryId();
            boolean isAdded = false;
            while(! fatherID.equals("-1")){
                if (fatherID.equals(CategoryID)){
                    list3.add(temp);
                    break;
                }
                else{
                    DBCategory dbCategory = searchCategory(fatherID);
                    fatherID = dbCategory.getCategoryPO().getFatherCategoryId();
                }
            }
        }
        return list3;
    }

    @Override
    public ArrayList<CommodityPO> getCommodity(LocalDate start, LocalDate end) throws RemoteException {
        getConnection();
        String sql = "select * from commoditytable where DATE(changetime) BETWEEN '" + start +"' and '" + end + "';";
        ArrayList<DBCommodity> dbCommodities = new ArrayList<>();
        ArrayList<CommodityPO> commodityPOS = new ArrayList<>();
        ArrayList<String> goodIDs = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String DBid = rs.getString(1);
                LocalDate DBdate = rs.getDate(2).toLocalDate();
                int DBamount = rs.getInt(3);
                double DBPrice = rs.getDouble(4);
                DBCommodity dbCommodity = new DBCommodity(DBid,DBdate,DBamount,DBPrice);
                dbCommodities.add(dbCommodity);
            }

            int len = dbCommodities.size();
            for (int i=0;i<len;i++){
                int insum = 0;
                int outsum = 0;
                double inM0 = 0;
                double outM0 = 0;
                DBCommodity temp = dbCommodities.get(i);
                if (temp.amount>0){
                    insum = temp.amount;
                    inM0 = insum*temp.price;
                }
                if (temp.amount<0){
                    outsum = 0-temp.amount;
                    outM0 = outsum*temp.price;
                }
                if (goodIDs.contains(temp.goodID)){
                    int index = goodIDs.indexOf(temp.goodID);
                    CommodityPO temp1 = commodityPOS.get(index);
                    temp1.setTotalInbound(temp1.getTotalInbound() + insum);
                    temp1.setTotalOutbound(temp1.getTotalOutbound() + outsum);
                    temp1.setTotalPurchaseVolume(temp1.getTotalPurchaseVolume() + inM0);
                    temp1.setTotalSalesVolume(temp1.getTotalSalesVolume() + outM0);
                }
                else{
                    goodIDs.add(temp.goodID);
                    DBGood dbGood = searchGoodByID(temp.goodID);
                    GoodPO goodPO = dbGood.getGoodPO();
                    CommodityPO commodityPO = new CommodityPO(temp.goodID,goodPO.getName(), goodPO.getType(),insum,outsum,outM0,inM0);
                    commodityPOS.add(commodityPO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodityPOS;
    }

    @Override
    public ResultMessage updateAmount(String GoodID, int difference, double price, LocalDate date) throws RemoteException {
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(commodity.getInsertSQL());
            stat.setString(1,GoodID);
            stat.setDate(2, java.sql.Date.valueOf(date));
            stat.setInt(3,difference);
            stat.setDouble(4,price);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        DBGood dbGood = searchGoodByID(GoodID);
        int amount = dbGood.getGoodPO().getNowAmount() + difference;
       String sql = "update goodtable set nowAmount = " + amount + " where goodID = '" + GoodID + "' ;";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage updateRecentSellingPrice(String GoodID, double difference) throws RemoteException {
        getConnection();
        double sellingPrice = difference;

        String sql = "update goodtable set recentSellingPrice = "+ sellingPrice + " where goodID = '" + GoodID + "' ;";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage updateRecentBuyingPrice(String GoodID, double difference) throws RemoteException {
        getConnection();

        double buyingPrice = difference;

        String sql = "update goodtable set recentBuyingPrice = "+ buyingPrice + " where goodID = '" + GoodID + "' ;";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage checkExistence(ArrayList<String> idList) throws RemoteException {
        return null;
    }

    //获得数据库连接
    private void getConnection(){
        if (conn == null){
            conn = DBUtil.open();
        }
    }

    //获得数据库需要的int类型categoryID
    private int getCategoryDBID(String id){
        String str = id.substring(1);
        return Integer.valueOf(str);
    }

    //获得po需要的categoryID
    private String getCategoryPOID(int id){
        String str = String.valueOf(id);
        int len = str.length();
        while(len < 6){
            str = "0" + str;
            len ++;
        }
        return "C"+ str;
    }

    private DBCategory searchCategory(String ID){
        try {
            PreparedStatement stat = conn.prepareStatement(category.getSelectSQL());
            stat.setInt(1,getCategoryDBID(ID));
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                int DBId = rs.getInt(1);
                String DBName = rs.getString(2);
                String DBfatherID = rs.getString(3);
                String DBsonID = rs.getString(4);
                int DBsonNumber = rs.getInt(5);
                CategoryPO categoryPO = new CategoryPO(getCategoryPOID(DBId),DBName,DBfatherID);
                DBCategory dbCategory = new DBCategory();
                dbCategory.categoryPO = categoryPO;
                dbCategory.sonID = DBsonID;
                dbCategory.sonNumber = DBsonNumber;
                return dbCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //用一个内部类暂存一堆数据库category的数据
    public class DBCategory{
        CategoryPO categoryPO;
        String sonID;
        int sonNumber;

        public String getSonID() {
            return sonID;
        }

        public int getSonNumber() {
            return sonNumber;
        }

        public CategoryPO getCategoryPO() {
            return categoryPO;
        }

        public void setSonNumber(int num){
            sonNumber = num;
        }

        public void setSonID(String sonID){
            this.sonID = sonID;
        }
    }

    //跟新categorytable的sonID和sonNumber
    private ResultMessage updatePartOfCategory(DBCategory dbCategory){
        String sql = "update " + tablename2 + " set sonID =  '" + dbCategory.getSonID() + " ' where "+ primaryKey2 + " = " +getCategoryDBID(dbCategory.getCategoryPO().getId()) + ";";
        String sql2= "update " + tablename2 + " set sonNumber = " + dbCategory.getSonNumber() + " where "+ primaryKey2 + " = " +getCategoryDBID(dbCategory.getCategoryPO().getId()) + ";";

        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;


    }

    //用内部内存储数据库goodtable字段
    public class DBGood{
        GoodPO goodPO;
        boolean isDeleted;

        GoodPO getGoodPO(){
            return goodPO;
        }
        boolean getIsDeleted(){
            return isDeleted;
        }
    }

    //通过id单一查找good
    private DBGood searchGoodByID(String goodid){
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(good.getSelectSQL());
            stat.setString(1,goodid);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                String DBgoodID = rs.getString(1);
                String DBgoodName = rs.getString(2);
                String DBfather = rs.getString(3);
                String DBtype = rs.getString(4);
                int DBworningAmount = rs.getInt(5);
                Double DBbuyingPrice = rs.getDouble(6);
                Double DBsellPrice = rs.getDouble(7);
                int DBnowAmount = rs.getInt(8);
                Double DBrecentBuyingPrice = rs.getDouble(9);
                Double DBrecentSellingPrice = rs.getDouble(10);
                boolean DBisDeleted = rs.getBoolean(11);
                String commend = rs.getString(12);
                GoodPO goodPO = new GoodPO(DBgoodID,DBgoodName,DBfather,DBtype,DBworningAmount,DBbuyingPrice,DBsellPrice,DBnowAmount,DBrecentBuyingPrice,DBrecentSellingPrice,commend);
                DBGood dbGood = new DBGood();
                dbGood.goodPO = goodPO;
                dbGood.isDeleted = DBisDeleted;
                return dbGood;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultMessage delGoodInDB(String id){
        getConnection();
        try {
            PreparedStatement stat = conn.prepareStatement(good.getDeleteSQL());
            stat.setString(1,id);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }finally {
            return ResultMessage.SUCCESS;
        }

    }

    //通过sql语句查找good返回goodpo列表
    private ArrayList<GoodPO> randomSearch(String sql){
        getConnection();
        ArrayList<GoodPO> list = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String DBgoodID = rs.getString(1);
                String DBgoodName = rs.getString(2);
                String DBfather = rs.getString(3);
                String DBtype = rs.getString(4);
                int DBworningAmount = rs.getInt(5);
                Double DBbuyingPrice = rs.getDouble(6);
                Double DBsellPrice = rs.getDouble(7);
                int DBnowAmount = rs.getInt(8);
                Double DBrecentBuyingPrice = rs.getDouble(9);
                Double DBrecentSellingPrice = rs.getDouble(10);
                boolean DBisDeleted = rs.getBoolean(11);
                String comment = rs.getString(12);
                GoodPO goodPO = new GoodPO(DBgoodID,DBgoodName,DBfather,DBtype,DBworningAmount,DBbuyingPrice,DBsellPrice,DBnowAmount,DBrecentBuyingPrice,DBrecentSellingPrice,comment);
                list.add(goodPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private ArrayList<GoodPO> getgoodPOS(){
        String sql = "select * from goodtable where isDeleted = false;";
        return randomSearch(sql);

    }

    private ArrayList<CategoryPO> getcategoryPOS(){
        getConnection();
        String sql = "select * from categorytable;";
        ArrayList<CategoryPO> categoryPOS = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String id = getCategoryPOID(rs.getInt(1));
                String name = rs.getString(2);
                String fatherID = rs.getString(3);
                CategoryPO categoryPO = new CategoryPO(id,name,fatherID);
                categoryPOS.add(categoryPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryPOS;
    }

    //用一个内部类存储commodity数据库数据
    public class DBCommodity{
        String goodID;
        LocalDate changetime;
        int amount;
        double price;
        DBCommodity(String goodID,LocalDate changetime,int amount,double price){
            this.goodID = goodID;
            this.changetime = changetime;
            this.amount = amount;
            this.price = price;
        }
        DBCommodity(){}
    }

}
