package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//数据库工具类
public class DBUtil {
    public static Connection connection=null;

    static {
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用 数据库名 做参数调用该方法，返回一个创建好的数据库链接
     * @param DB
     * @return Connection
     */
    public static Connection getConnection(String DB){
        try {
            //获得数据库链接
            connection= DriverManager.getConnection("jdbc:mysql://localhost:2333/"+DB+"?serverTimezone=Asia/Shanghai","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
