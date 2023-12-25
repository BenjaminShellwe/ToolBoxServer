package top.shellwe.toolbox.repository;

import top.shellwe.toolbox.controller.UserDTO;
import top.shellwe.toolbox.repository.tools.OtherTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AMCMUserRepository {

    private final DatabaseConnection databaseConnection;

    public AMCMUserRepository() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

//    获取所有信息
    public List<UserDTO> getAllUserInfoWithRoles() {
        List<UserDTO> userList = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM user_info";
                try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
                    ResultSet userInfoResultSet = statement.executeQuery();
                    while (userInfoResultSet.next()) {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUSER_ID(userInfoResultSet.getString("USER_ID"));
                        userDTO.setUSER_LOGIN_ID(userInfoResultSet.getString("USER_LOGIN_ID"));
                        userDTO.setUSER_NAME(userInfoResultSet.getString("USER_NAME"));
                        userDTO.setGROUP_ID(userInfoResultSet.getString("GROUP_ID"));
                        userDTO.setDEPART_NAME(userInfoResultSet.getString("DEPART_NAME"));
                        userDTO.setUSER_PASS(userInfoResultSet.getString("USER_PASS"));
                        userDTO.setUSERPWD_LAPSE(userInfoResultSet.getString("USERPWD_LAPSE"));
                        userDTO.setSETPWD_LAPSE(userInfoResultSet.getString("SETPWD_LAPSE"));
                        userDTO.setMODIFY_TIME(userInfoResultSet.getString("MODIFY_TIME"));
                        userDTO.setUSER_STATE(userInfoResultSet.getString("USER_STATE"));
                        userDTO.setONLINE_STATE(userInfoResultSet.getString("ONLINE_STATE"));
                        userDTO.setREMARK(userInfoResultSet.getString("REMARK"));
                        userDTO.setUSER_TYPE(userInfoResultSet.getString("USER_TYPE"));
                        userDTO.setOLD_PASSWORDS(userInfoResultSet.getString("OLD_PASSWORDS"));
                        userDTO.setSYNC_FLAG(userInfoResultSet.getString("SYNC_FLAG"));
                        // Set other user-related fields from user_info table

                        String userId = userInfoResultSet.getString("USER_ID");
                        String selectUserRoleQuery = "SELECT * FROM user_role WHERE USER_ID = ?";
                        try (PreparedStatement userRoleStatement = connection.prepareStatement(selectUserRoleQuery)) {
                            userRoleStatement.setString(1, userId);
                            ResultSet userRoleResultSet = userRoleStatement.executeQuery();
                            while (userRoleResultSet.next()) {
                                String roleId = userRoleResultSet.getString("ROLE_ID");
                                String selectRoleInfoQuery = "SELECT * FROM role_info WHERE ROLE_ID = ?";
                                try (PreparedStatement roleInfoStatement = connection.prepareStatement(selectRoleInfoQuery)) {
                                    roleInfoStatement.setString(1, roleId);
                                    ResultSet roleInfoResultSet = roleInfoStatement.executeQuery();
                                    while (roleInfoResultSet.next()) {
                                        userDTO.setROLE_ID(roleInfoResultSet.getString("ROLE_ID"));
                                        userDTO.setROLE_TYPE(roleInfoResultSet.getString("ROLE_TYPE"));
                                        userDTO.setROLE_NAME(roleInfoResultSet.getString("ROLE_NAME"));
                                        // Populate role-related fields from role_info table
                                    }
                                }
                            }
                        }

                        userList.add(userDTO);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
//    创建新用户
    public String createUser(String userLoginID, String userName, String userPass, String departName, String remark) {
        if(!OtherTools.checkString(userPass)){
            return "The error is that the password doesn't match the rules.";
        }
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO user_info (USER_LOGIN_ID, USER_NAME, USER_PASS, DEPART_NAME, REMARK) " +
                        "VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                    statement.setString(1, userLoginID);
                    statement.setString(2, userName);
                    statement.setString(3, userPass);
                    statement.setString(4, departName);
                    statement.setString(5, remark);

                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                return "The error is " + e.getMessage().split("for")[0];
            }
        }
        return "Success!";
    }

//    更新用户信息
    public String updateUser(int userId, String userName, String loginId, String password, String department, String remark) {
        if(!OtherTools.checkString(password)){
            return "The error is that the password doesn't match the rules.";
        }
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String sql = "UPDATE user_info SET USER_NAME = ?, USER_LOGIN_ID = ?, USER_PASS = ?, DEPART_NAME = ?, REMARK = ? WHERE USER_ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userName);
                statement.setString(2, loginId);
                statement.setString(3, password);
                statement.setString(4, department);
                statement.setString(5, remark);
                statement.setInt(6, userId);

                statement.executeUpdate();
            } catch (SQLException e) {
//                e.printStackTrace();
                return "The error is " + e.getMessage().split("for")[0];
            }
        }
        return "Success!";
    }

//    修改状态
    public String updateUserState(int userId, int userState) {
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String sql = "UPDATE user_info SET USER_STATE = ? WHERE USER_ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userState);
                statement.setInt(2, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
//                e.printStackTrace();
                return "The error is " + e.getMessage().split("for")[0];
            }
        }
        return "Success!";
    }
//  获取角色表内容
public List<UserDTO> getAllRoles() {
    List<UserDTO> roles = new ArrayList<>();
    Connection connection = databaseConnection.getConnection();
    if (connection != null) {
        String sql = "SELECT * FROM role_info";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roleId = resultSet.getString("ROLE_ID");
                String roleType = resultSet.getString("ROLE_TYPE");
                String roleName = resultSet.getString("ROLE_NAME");

                UserDTO roleInfo = new UserDTO(roleId, roleType, roleName);
                roles.add(roleInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return roles;
}

    public static void main(String[] args) {
        AMCMUserRepository a = new AMCMUserRepository();
        String str = a.updateUserState(225005, 2);
        System.out.println(str);
    }
}
