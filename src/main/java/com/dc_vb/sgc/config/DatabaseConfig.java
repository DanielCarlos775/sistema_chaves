package com.dc_vb.sgc.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .load();

    private static final String URL = "jdbc:mysql://" +
            dotenv.get("DB_HOST") + ":3306/" +
            dotenv.get("DB_NAME") +
            "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&connectTimeout=5000";

    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASS");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
