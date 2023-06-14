package com.caserisimo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {
    private static Session instance;
    private Connection connection;

    private Session() {
    }
    public static Session getInstance() {
        if (instance == null) {
            synchronized (Session.class) {
                if (instance == null) {
                    instance = new Session();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:sqlite:identifier.sqlite";
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
