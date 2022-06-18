package de.zevyx.kovacore.api;

import de.zevyx.kovacore.KovaCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAPI {

    private Connection connection;

    public DatabaseAPI() {

    }

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() {
        if(!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + KovaCore.getInstance().getMySQLConfig().getHost() + ":" + KovaCore.getInstance().getMySQLConfig().getPort() + "/" + KovaCore.getInstance().getMySQLConfig().getDatabase(), KovaCore.getInstance().getMySQLConfig().getUsername(), KovaCore.getInstance().getMySQLConfig().getPassword());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
