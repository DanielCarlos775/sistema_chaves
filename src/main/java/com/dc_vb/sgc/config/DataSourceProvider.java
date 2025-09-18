package com.dc_vb.sgc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;
import java.util.Optional;
import java.sql.Connection;
import java.sql.SQLException;

public final class DataSourceProvider {

    private static final HikariDataSource DATASOURCE;

    static {
        // Carrega .env.dev.<env>. Ex: -Denv=dev -> .env.dev.dev
        Dotenv dotenv = Dotenv.configure()
                .filename(".env.dev." + System.getProperty("env", "dev"))
                .ignoreIfMissing()  // permite rodar sem arquivo (ex: CI com env vars)
                .load();

        String host = env(dotenv, "DB_HOST", "localhost");
        String port = env(dotenv, "DB_PORT", "3306");
        String db   = env(dotenv, "DB_NAME", "sistema_chaves");
        String user = env(dotenv, "DB_USER", "sgc_app_dev");
        String pass = env(dotenv, "DB_PASSWORD", "Daniel123");

        int maxPool = Integer.parseInt(env(dotenv, "DB_POOL_MAX", "10"));

        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + db
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        HikariConfig cfg = new HikariConfig();
        cfg.setPoolName("SGC-HikariPool");
        cfg.setJdbcUrl(jdbcUrl);
        cfg.setUsername(user);
        cfg.setPassword(pass);
        cfg.setMaximumPoolSize(maxPool);
        cfg.setMinimumIdle(Math.max(2, maxPool / 4));
        cfg.setConnectionTimeout(10_000);
        cfg.setIdleTimeout(60_000);
        cfg.setMaxLifetime(30 * 60_000); // 30 min

        // Otimizações MySQL
        cfg.addDataSourceProperty("cachePrepStmts", "true");
        cfg.addDataSourceProperty("prepStmtCacheSize", "250");
        cfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        cfg.addDataSourceProperty("useServerPrepStmts", "true");
        cfg.addDataSourceProperty("useLocalSessionState", "true");
        cfg.addDataSourceProperty("rewriteBatchedStatements", "true");
        cfg.addDataSourceProperty("cacheResultSetMetadata", "true");
        cfg.addDataSourceProperty("cacheServerConfiguration", "true");
        cfg.addDataSourceProperty("elideSetAutoCommits", "true");
        cfg.addDataSourceProperty("maintainTimeStats", "false");

        DATASOURCE = new HikariDataSource(cfg);

        // Hook de shutdown limpo (opcional)
        Runtime.getRuntime().addShutdownHook(new Thread(DATASOURCE::close, "SGC-DS-Shutdown"));
    }

    private DataSourceProvider() { /* no-op */ }

    public static Connection getConnection() throws SQLException {
        return DATASOURCE.getConnection();
    }

    private static String env(Dotenv d, String key, String def) {
        String value = System.getenv(key);       // primeiro tenta variável de ambiente real
        if (value == null) {
            value = d.get(key);                  // depois tenta no arquivo .env.dev
        }
        return (value != null && !value.isBlank()) ? value : def;
    }
}
