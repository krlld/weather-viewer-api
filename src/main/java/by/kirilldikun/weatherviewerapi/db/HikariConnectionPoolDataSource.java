package by.kirilldikun.weatherviewerapi.db;

import by.kirilldikun.weatherviewerapi.common.util.PropertySource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HikariConnectionPoolDataSource {

    private static final HikariConfig config = new HikariConfig();

    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl(PropertySource.get("database.url"));
        config.setUsername(PropertySource.get("database.username"));
        config.setPassword(PropertySource.get("database.password"));
        config.setDriverClassName("org.postgresql.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
