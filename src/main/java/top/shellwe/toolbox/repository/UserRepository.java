package top.shellwe.toolbox.repository;

import top.shellwe.toolbox.controller.UserDTO;

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

    public int afterValidateUser(String username, String password) {
//        我就之后后面肯定冲突
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public UserDTO getUserDTO(int userId) {
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT full_name, gender, phone, role FROM user_info WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("full_name");
                    String gender = resultSet.getString("gender");
                    String phone = resultSet.getString("phone");
                    String role = resultSet.getString("role");


                    return new UserDTO(fullName, gender, phone, role);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // 或者抛出异常，具体取决于你的需求
    }

}
