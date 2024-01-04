package top.shellwe.toolbox.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.shellwe.toolbox.repository.tools.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class DatabaseConnection {
    private Connection connection;
    private static DatabaseConnection instance = null;

    @Autowired
    private DatabaseProperties databaseProperties;

    @PostConstruct
    private void initializeConnection() {
        try {
            String url = databaseProperties.getUrl();
            String username = databaseProperties.getUsername();
            String password = databaseProperties.getPassword();

            connection = DriverManager.getConnection(url, username, password);
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
