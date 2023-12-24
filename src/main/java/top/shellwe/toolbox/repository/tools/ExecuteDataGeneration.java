package top.shellwe.toolbox.repository.tools;

import top.shellwe.toolbox.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ExecuteDataGeneration {
    private final DatabaseConnection databaseConnection;

    public ExecuteDataGeneration() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    public boolean addData(){
        Connection connection = databaseConnection.getConnection();
        if (connection != null) {
            String insertQuery = "INSERT INTO user_info (USER_ID, USER_LOGIN_ID, USER_NAME, GROUP_ID, DEPART_NAME, " +
                    "USER_PASS, SETPWD_LAPSE, MODIFY_TIME, USER_STATE, ONLINE_STATE, USER_TYPE, " +
                    "REMARK, OLD_PASSWORDS, SYNC_FLAG, TRY_PASSWORD) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                for (int i = 0; i < 93; i++) {
                    statement.setString(1, String.valueOf(1225009 + i + 1)); // USER_ID starting from 1225009
                    statement.setString(2, "employer0" + (6 + i + 1)); // USER_LOGIN_ID starting from employer007
                    statement.setString(3, "employern" + (i + 2)); // USER_NAME starting from employern2
                    statement.setInt(4, 2); // GROUP_ID default 2
                    statement.setString(5, "2"); // DEPART_NAME default 2
                    statement.setString(6, "123456"); // USER_PASS default 123456

//                    statement.setNull(7, java.sql.Types.CHAR); // USERPWD_LAPSE empty

                    statement.setDate(7, java.sql.Date.valueOf("2024-12-06")); // SETPWD_LAPSE default 2024-12-06
                    statement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now())); // MODIFY_TIME current timestamp
                    statement.setInt(9, 0); // USER_STATE default 0
                    statement.setInt(10, 0); // ONLINE_STATE default 0
                    statement.setInt(11, 1); // USER_TYPE default 1

                    statement.setString(12, null); // REMARK empty
                    statement.setString(13, null); // OLD_PASSWORDS empty
                    statement.setString(14, null); // SYNC_FLAG empty
                    statement.setString(15, "0"); // TRY_PASSWORD default 0

                    statement.addBatch();
                }
                statement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ExecuteDataGeneration e = new ExecuteDataGeneration();
        System.out.println(e.addData());
    }

}
