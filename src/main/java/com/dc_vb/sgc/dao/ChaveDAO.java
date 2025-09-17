package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Chaves;
import com.dc_vb.sgc.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChaveDAO {

    private final Connection connection;

    public ChaveDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Chaves chave) throws SQLException {
        String sql = "INSERT INTO chaves (codigo, id_sala) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, chave.getId_chave());
            stmt.setInt(2, chave.getId_sala());
            stmt.executeUpdate();
        }
    }

    public void update(Chaves chave) throws SQLException {
        String sql = "UPDATE chaves SET codigo = ?, id_sala = ? WHERE id_chave = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chave.getCodigo());
            stmt.setInt(2, chave.getId_sala());
            stmt.setInt(3, chave.getId_chave());
            stmt.executeUpdate();
        }
    }

    public void delete(int id_chave) throws SQLException {
        String sql = "DELETE FROM chaves WHERE id_chave = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id_chave);
            stmt.executeUpdate();
        }
    }

    public List<Chaves> findAll() throws SQLException {
        List<Chaves> chave = new ArrayList<>();
        String sql = "SELECT * FROM chaves";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Chaves chaves = new Chaves(rs.getInt("id_chave"), rs.getString("codigo"), rs.getInt("id_sala"));
                chave.add(chaves);
            }
        }
        return chave;
    }

    public Chaves findById(int id_chave) throws SQLException {
        String sql = "SELECT * FROM chaves WHERE id_chave = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id_chave);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Chaves(rs.getInt("id_chave"), rs.getString("codigo"), rs.getInt("id_sala"));
                }
            }
        }
        return null;
    }
}
