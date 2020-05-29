package model;

import java.sql.*;

/**
 * 数据库工具类
 * <p>
 * 用来获取数据库连接
 */


public class DbUtil {
    public static Connection connection = null;

    static String dbName = "netdisc";

    static {
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用 数据库名 做参数调用该方法，返回一个创建好的数据库连接
     *
     * @param db
     * @return Connection
     */
    public static Connection getConnection(String db) {
        try {
            //获得数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?serverTimezone=Asia/Shanghai", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 执行查询语句，选择某些列 返回该列的值 （int 类型）
     *
     * @param SQL
     * @param colName
     * @return
     */
    public static int selectToInt(String SQL, String colName) {
        Connection connection = getConnection(dbName);
        int data = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                data = resultSet.getInt(colName);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return data;
        }
    }

    /**
     * 执行查询语句，选择某些列 返回该列的值 （String 类型）
     *
     * @param SQL
     * @param colName
     * @return
     */
    public static String selectToString(String SQL, String colName) {
        Connection connection = getConnection(dbName);
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ResultSet selectToSet(String SQL) {
        Connection connection = getConnection(dbName);
        ResultSet resultSet=null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }
    }

    public static boolean execute(String SQL) {
        Connection connection = getConnection(dbName);
        boolean successful = false;
        try {
            Statement statement = connection.createStatement();
            successful = statement.execute(SQL);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return successful;

        }
    }
}
