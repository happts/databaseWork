package utils_ex;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create by PstereoM on 2018/4/23 15:52
 **/
public class DBManager {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://101.132.32.106:3306/hmj", "guest0", "guest0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void Close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试数据库连接是否成功
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(DBManager.getConnection());
    }
}
