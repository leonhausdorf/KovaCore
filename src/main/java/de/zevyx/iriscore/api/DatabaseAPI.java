package de.zevyx.iriscore.api;

import de.zevyx.iriscore.IrisCore;

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
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + IrisCore.getInstance().getMySQLConfig().getHost() + ":" + IrisCore.getInstance().getMySQLConfig().getPort() + "/" + IrisCore.getInstance().getMySQLConfig().getDatabase(), IrisCore.getInstance().getMySQLConfig().getUsername(), IrisCore.getInstance().getMySQLConfig().getPassword());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
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
