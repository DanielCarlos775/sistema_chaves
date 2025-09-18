package com.dc_vb.sgc.util;

import com.dc_vb.sgc.config.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT VERSION() as v");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("Conexão OK. MySQL versão: " + rs.getString("v"));
            } else {
                System.out.println("Conexão OK, mas não obtive versão.");
            }

        } catch (Exception e) {
            System.err.println("Falha na conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
