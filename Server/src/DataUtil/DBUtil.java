package DataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String driver;
    private static String userName;
    private static String url;
    private static String password;

    static {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/XiangXiangDeng?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        userName = "XiangXiangDeng";
        password = "123456";
    }

    //连接数据库
    public static Connection open(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库
    public static void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
