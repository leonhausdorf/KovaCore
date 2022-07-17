package de.zevyx.iriscore.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.zevyx.iriscore.IrisCore;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAPI {

    private static final HikariConfig dbConfig;

     static {
        dbConfig = new HikariConfig();
        dbConfig.setJdbcUrl("jdbc:mysql://" + IrisCore.getInstance().getMySQLConfig().getHost() + ":" + IrisCore.getInstance().getMySQLConfig().getPort() + "/" + IrisCore.getInstance().getMySQLConfig().getDatabase());
        dbConfig.setUsername(IrisCore.getInstance().getMySQLConfig().getUsername());
        dbConfig.setPassword(IrisCore.getInstance().getMySQLConfig().getPassword());
        dbConfig.setDriverClassName("com.mysql.jdbc.Driver");
        dbConfig.addDataSourceProperty("cachePrepStmts", "true");
        dbConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        dbConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    private static final HikariDataSource ds = new HikariDataSource(dbConfig);

    public static <T> T execute(ConnectionCallback<T> callback) {
        try (Connection conn = ds.getConnection()) {
            return callback.doInConnection(conn);
        } catch (SQLException e) {
            throw new IllegalStateException("Error during execution.", e);
        }
    }

    public static interface ConnectionCallback<T> {
        public T doInConnection(Connection conn) throws SQLException;
    }
}
