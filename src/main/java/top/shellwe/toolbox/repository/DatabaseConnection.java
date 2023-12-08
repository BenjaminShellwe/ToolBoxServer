package top.shellwe.toolbox.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection connection;

    private static final String DB_URL = "jdbc:mysql://your_server:3306/your_table";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_passwoed";

    private DatabaseConnection() {
        try {
            // 创建数据库连接
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
