package DataUtil;

import java.sql.Connection;

public class DBHelper {

    //四种sql语句模型
    private String insertSQL;
    private String deleteSQL;
    private String updateSQL;
    private String selectSQL;

    //每张表的属性
    private String tableName;
    private String primaryKey;
    private int parameterNumber;

    //获得连接
    protected Connection conn = null;

    //默认构造函数
    public DBHelper() {
    }

    //重载构造函数
    public DBHelper(String name,String key,int num){
        this.tableName = name;
        this.primaryKey = key;
        this.parameterNumber = num;
        connect();
    }

    //返回insert语句
    public String getInsertSQL(){
        insertSQL = "insert into " + tableName + " values(";
        for (int i = 0;i<parameterNumber-1;i++){
            insertSQL = insertSQL + "?,";
        }
        insertSQL = insertSQL + "?);";

       return insertSQL;
    }

    //返回delete语句
    public String getDeleteSQL(){
        deleteSQL = "delete from " + tableName + " where " + primaryKey + " = ?;";
        return  deleteSQL;
    }

    //返回update语句
    public String getUpdateSQL(){
        updateSQL = "update " + tableName + " set ? = ? where "+ primaryKey + " = ?;";
        return updateSQL;
    }

    //返回select语句
    public String getSelectSQL(){
        selectSQL = "select * from " + tableName + " where " + primaryKey + " = ?;";
        return selectSQL;
    }

    private void connect() {
        conn = DBUtil.open();
    }

}
