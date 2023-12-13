package top.shellwe.toolbox.repository;

import top.shellwe.toolbox.controller.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    String fullname = resultSet.getString("full_name");
                    String gender = resultSet.getString("gender");
                    String phone = resultSet.getString("phone");
                    String role = resultSet.getString("role");


                    return new UserDTO(fullname, gender, phone, role);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public List<UserDTO> getUsersWithoutAdmin() {
        List<UserDTO> userDTOList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT u.id, u.username, u.password, ui.full_name, ui.gender, ui.phone, ui.role " +
                    "FROM users u " +
                    "INNER JOIN user_info ui ON u.id = ui.user_id " +
                    "WHERE ui.role <> '00'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String fullname = resultSet.getString("full_name");
                    String gender = resultSet.getString("gender");
                    String phone = resultSet.getString("phone");
                    String role = resultSet.getString("role");

                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(id);
                    userDTO.setUsername(username);
                    userDTO.setPassword(password);
                    userDTO.setFullname(fullname);
                    userDTO.setGender(gender);
                    userDTO.setPhone(phone);
                    userDTO.setRole(role);

                    userDTOList.add(userDTO);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userDTOList;
    }

    public boolean updateUserInfo(int userId, String fullName, String gender, String phone, String role) {
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            StringBuilder queryBuilder = new StringBuilder("UPDATE user_info SET ");

            List<String> columnsToUpdate = new ArrayList<>();
            if (fullName != null) {
                columnsToUpdate.add("full_name = ?");
            }
            if (gender != null) {
                columnsToUpdate.add("gender = ?");
            }
            if (phone != null) {
                columnsToUpdate.add("phone = ?");
            }
            if (role != null) {
                columnsToUpdate.add("role = ?");
            }

            queryBuilder.append(String.join(", ", columnsToUpdate));
            queryBuilder.append(" WHERE user_id = ?");

            try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
                int parameterIndex = 1;
                if (fullName != null) {
                    statement.setString(parameterIndex++, fullName);
                }
                if (gender != null) {
                    statement.setString(parameterIndex++, gender);
                }
                if (phone != null) {
                    statement.setString(parameterIndex++, phone);
                }
                if (role != null) {
                    statement.setString(parameterIndex++, role);
                }
                statement.setInt(parameterIndex, userId);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean insertUserAndUserInfo(String username, String password, String fullName, String gender, String phone, String role, int userId) {
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);

                // Insert into 'users' table
                String insertUserQuery = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
                PreparedStatement userStatement = connection.prepareStatement(insertUserQuery);
                userStatement.setInt(1, userId);
                userStatement.setString(2, username);
                userStatement.setString(3, password);
                userStatement.executeUpdate();

                // Insert into 'user_info' table
                String insertUserInfoQuery = "INSERT INTO user_info (user_id, full_name, gender, phone, role) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement userInfoStatement = connection.prepareStatement(insertUserInfoQuery);
                userInfoStatement.setInt(1, userId);
                userInfoStatement.setString(2, fullName);
                userInfoStatement.setString(3, gender);
                userInfoStatement.setString(4, phone);
                userInfoStatement.setString(5, role);
                userInfoStatement.executeUpdate();

                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean deleteUserAndUserInfoByUserId(int userId) {
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);

                // Delete from 'user_info' table
                String deleteUserInfoQuery = "DELETE FROM user_info WHERE user_id = ?";
                PreparedStatement userInfoStatement = connection.prepareStatement(deleteUserInfoQuery);
                userInfoStatement.setInt(1, userId);
                userInfoStatement.executeUpdate();

                // Delete from 'users' table
                String deleteUserQuery = "DELETE FROM users WHERE id = ?";
                PreparedStatement userStatement = connection.prepareStatement(deleteUserQuery);
                userStatement.setInt(1, userId);
                userStatement.executeUpdate();

                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        UserRepository u = new UserRepository();
        u.deleteUserAndUserInfoByUserId(120717);
    }

}
