package com.caserisimo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {
    private static Session instance;

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
        try {
            return DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection sessionConnection) {
        if (sessionConnection != null) {
            try {
                sessionConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
