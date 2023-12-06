package top.shellwe.toolbox.controller;

import top.shellwe.toolbox.Repository.DatabaseConnection;

import java.sql.Connection;

public class Tester {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();

        if (connection != null) {
            System.out.println("数据库连接成功！");
            // 这里可以进行其他数据库操作的测试
        } else {
            System.out.println("数据库连接失败！");
        }

        // 最后关闭数据库连接
        databaseConnection.closeConnection();
    }
}