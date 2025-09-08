package com.dc_vb.sgc.test;

import com.dc_vb.sgc.config.DatabaseConfig;
import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexão bem-sucedida com o banco de dados!");
            } else {
                System.out.println("❌ Falha na conexão.");
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar ao banco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
