package Data;

import DataService.CustomerDataService;
import DataUtil.DBHelper;
import DataUtil.DBUtil;
import PO.CustomerPO;
import Util.CustomerKinds;
import Util.EmptyValue;
import Util.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDataImpl extends DBHelper implements CustomerDataService {

    private static final String tableName = "customertable";

    private static final String primaryKey = "customerID";

    private static final int parameterNumber = 14;



    //重载构造函数

    public CustomerDataImpl(){

        super(tableName,primaryKey,parameterNumber);

    }



    //逐一给预备语句赋值（insert和update要用）

    private PreparedStatement setInsert(PreparedStatement stat,CustomerPO po) throws SQLException {

        stat.setNull(1, Types.INTEGER);

        stat.setString(2,po.getCustomerName());

        stat.setString(3,po.getCustomerKinds().toString());

        stat.setInt(4,po.getLevel());

        stat.setString(5,po.getTelNumber());

        stat.setString(6,po.getZipCode());

        stat.setString(7,po.getEmail());

        stat.setDouble(8,po.getQuata());

        stat.setDouble(9,po.getReceivable());

        stat.setDouble(10,po.getReceivable());

        stat.setString(11,po.getClerk());

        stat.setString(12,po.getAddress());

        stat.setBoolean(13,false);
        stat.setString(14,po.getComment());
        return stat;

    }



    @Override

    public ResultMessage insert(CustomerPO po) throws RemoteException {

        getConnection();

        try {

            PreparedStatement stat = conn.prepareStatement(getInsertSQL());

            stat = setInsert(stat,po);

            stat.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

            return ResultMessage.FAILED;

        }finally {

            return ResultMessage.SUCCESS;

        }

    }



    private ResultMessage delete(String id) throws RemoteException {

        getConnection();

        try {

            PreparedStatement stat = conn.prepareStatement(getDeleteSQL());

            stat.setInt(1,getIndex(id));

            stat.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

            return ResultMessage.FAILED;

        }finally {

            return ResultMessage.SUCCESS;

        }

    }



    @Override

    public ResultMessage update(CustomerPO po) throws RemoteException {

        CustomerPO temp = searchByID(po.getCustomerId());

        if (po.getCustomerName().equals(EmptyValue.getString())){

            po.setCustomerName(temp.getCustomerName());

        }

        if (po.getCustomerKinds() == CustomerKinds.EMPTY){

            po.setRole(temp.getCustomerKinds());

        }

        if (po.getLevel() == EmptyValue.getInteger()){

            po.setLevel(temp.getLevel());

        }

        if (po.getTelNumber().equals(EmptyValue.getString())){

            po.setTelNumber(temp.getTelNumber());

        }

        if (po.getZipCode().equals(EmptyValue.getString())){

            po.setZipCode(temp.getZipCode());

        }

        if (po.getEmail().equals(EmptyValue.getString())){

            po.setEmail(temp.getEmail());

        }

        if (po.getQuata() == EmptyValue.getDouble()){

            po.setQuata(temp.getQuata());

        }

        if (po.getReceivable() == EmptyValue.getDouble()){

            po.setReceivable(temp.getReceivable());

        }

        if (po.getPayable() == EmptyValue.getDouble()){

            po.setReceivable(temp.getPayable());

        }

        if (po.getClerk().equals(EmptyValue.getString())){

            po.setClerk(temp.getClerk());

        }

        if (po.getAddress().equals(EmptyValue.getString())){

            po.setAddress(temp.getAddress());

        }

        getConnection();

        delete(po.getCustomerId());

        getConnection();

        try {

            PreparedStatement stat = conn.prepareStatement(getInsertSQL());

            stat = setInsert(stat,po);

            stat.setInt(1,getIndex(po.getCustomerId()));

            stat.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

            return ResultMessage.FAILED;

        }finally {

            return ResultMessage.SUCCESS;

        }

    }



    @Override

    public ArrayList<CustomerPO> searchCustomers(String str) throws RemoteException {

        String sql1 = "select * from customertable where customerName like '%"+ str + "%' and isDeleted = false";

        ArrayList<CustomerPO> list1 = getList(sql1);

        String sql2 = "select * from customertable where customerID like '%"+ str + "%'and isDeleted = false;";

        ArrayList<CustomerPO> list2 = getList(sql2);

        int len = list2.size();

        for (int i=0;i<len;i++){

            CustomerPO temp = list2.get(i);

            list1.add(temp);

        }

        return list1;

    }



    @Override

    public CustomerPO searchByID(String id) throws RemoteException {

        try {

            PreparedStatement stat = conn.prepareStatement(getSelectSQL());

            stat.setInt(1,getIndex(id));

            ResultSet rs =  stat.executeQuery();

            while(rs.next()){

                int DBId = rs.getInt(1);

                String name = rs.getString(2);

                String kind = rs.getString(3);

                int level = rs.getInt(4);

                String number = rs.getString(5);

                String code = rs.getString(6);

                String email = rs.getString(7);

                double quata = rs.getDouble(8);

                double receivable = rs.getDouble(9);

                double payable = rs.getDouble(10);

                String clerk = rs.getString(11);

                String address = rs.getString(12);
                String commend = rs.getString(14);

                CustomerPO customerPO = new CustomerPO(getID(DBId,kind),name,getKinds(kind),level,number,address,code,email,quata,receivable,payable,clerk,commend);

                return customerPO;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }



    @Override

    public ArrayList<CustomerPO> getSupplier() throws RemoteException {

        ArrayList<CustomerPO> customerPOS = getCustomer();
        ArrayList<CustomerPO> result = new ArrayList<>();
        int len = customerPOS.size();
        for(int i=0;i<len;i++){
            CustomerPO temp = customerPOS.get(i);
            if (temp.getCustomerKinds() == CustomerKinds.SUPPLIER){
                result.add(temp);
            }
        }

        System.out.println(result);
        return result;

    }



    @Override

    public ArrayList<CustomerPO> getSaler() throws RemoteException {

        ArrayList<CustomerPO> customerPOS = getCustomer();
        ArrayList<CustomerPO> result = new ArrayList<>();
        int len = customerPOS.size();
        for(int i=0;i<len;i++){
            CustomerPO temp = customerPOS.get(i);
            if (temp.getCustomerKinds() == CustomerKinds.SALER){
                result.add(temp);
            }
        }

        System.out.println(result);
        return result;
    }



    @Override

    public ArrayList<CustomerPO> getCustomer() throws RemoteException {

        String sql = "select * from customertable where isDeleted = false";

        ArrayList<CustomerPO> customerPOS = getList(sql);

        System.out.println(customerPOS);
        return customerPOS;

    }



    private void getConnection(){

        if (conn == null){

            conn = DBUtil.open();

        }

    }



    /**

     *

     *  数据库存的id是int类型的，po的id是String类型的，格式为SAL000001或SUP000002

     * @param id

     * @return

     */

    private int getIndex(String id){

        String temp = id.substring(3);

        return Integer.valueOf(temp);

    }



    /**

     *

     * 返回po需要的id

     * @param DBid database id

     * @param kind database customerKinds

     * @return

     */

    private String getID(int DBid,String kind){

        String str = String.valueOf(DBid);

        int len = str.length();

        while(len<6){

            str = "0" + str;

            len++;

        }

        if (kind.equals("SUPPLIER")){

            str = "SUP"+str;

        }

        else{

            str = "SAL"+str;

        }

        return str;

    }



    private CustomerKinds getKinds(String kind){

        if (kind.equals("SUPPLIER")){

            return CustomerKinds.SUPPLIER;

        }

        else{

            return CustomerKinds.SALER;

        }

    }



    private ArrayList<CustomerPO> getList(String sql){

        getConnection();

        ArrayList<CustomerPO> list = new ArrayList<>();

        try {

            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);

            while(rs.next()){

                int DBId = rs.getInt(1);

                String name = rs.getString(2);

                String kind = rs.getString(3);

                int level = rs.getInt(4);

                String number = rs.getString(5);

                String code = rs.getString(6);

                String email = rs.getString(7);

                double quata = rs.getDouble(8);

                double receivable = rs.getDouble(9);

                double payable = rs.getDouble(10);

                String clerk = rs.getString(11);

                String address = rs.getString(12);

                String commend = rs.getString(14);

                CustomerPO customerPO = new CustomerPO(getID(DBId,kind),name,getKinds(kind),level,number,address,code,email,quata,receivable,payable,clerk,commend);

                list.add(customerPO);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;

    }



    @Override

    public ResultMessage disguiseDeleteCustomer(String id){

        String sql = "update " + tableName + " set isDeleted = true where "+ primaryKey + " = " + getIndex(id)+ ";";

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
