package gr.aueb.cf.school2024app.service.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static BasicDataSource ds = new BasicDataSource();
    private static Connection conn;

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/myschool2024?serverTimezone=UTC");
        ds.setUsername("school2024_user");
        ds.setPassword(System.getenv("pass_school2024DB"));
        //This settings should be compatible with tomcat configs
        ds.setInitialSize(10);
        ds.setMaxTotal(50);
        ds.setMinIdle(10);
        ds.setMaxIdle(10);

    }

    private DBUtil() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            //In enterprise apps where dependencies are used
            //MySql driver is not loading automatically into the memory
            //.forName LOADS the included class in memory
            //so then we can use instances
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = ds.getConnection();
            return conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
