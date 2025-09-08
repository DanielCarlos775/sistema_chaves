package com.dc_vb.sgc.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        // Lendo do arquivo config.properties
        config.setJdbcUrl(EnvReader.get("db.url"));
        config.setUsername(EnvReader.get("db.user"));
        config.setPassword(EnvReader.get("db.password"));

        config.setMaximumPoolSize(Integer.parseInt(EnvReader.get("db.pool.maxSize")));
        config.setMinimumIdle(Integer.parseInt(EnvReader.get("db.pool.minIdle")));
        config.setIdleTimeout(Long.parseLong(EnvReader.get("db.pool.idleTimeout")));
        config.setMaxLifetime(Long.parseLong(EnvReader.get("db.pool.maxLifetime")));
        config.setConnectionTimeout(Long.parseLong(EnvReader.get("db.pool.connectionTimeout")));

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}

