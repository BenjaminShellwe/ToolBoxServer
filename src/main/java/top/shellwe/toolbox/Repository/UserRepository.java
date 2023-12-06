package top.shellwe.toolbox.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final DatabaseConnection databaseConnection;

    public UserRepository() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    public boolean validateUser(String username, String password) {
//        先这么设置吧
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next(); // 如果存在匹配的用户则返回 true
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
