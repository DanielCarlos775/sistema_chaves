package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Salas;
import com.dc_vb.sgc.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    private final Connection connection;

    public SalaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Salas salas) throws SQLException {
        String sql = "INSERT INTO salas (nome, descricao, capacidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, salas.getNome());
            stmt.setString(2, salas.getDescricao());
            stmt.setInt(3, salas.getCapacidade());
            stmt.executeUpdate();
        }
    }

    public void update(Salas salas) throws SQLException {
        String sql = "UPDATE salas SET nome = ?, bloco = ? WHERE id_sala = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, salas.getNome());
            stmt.setString(2, salas.getDescricao());
            stmt.setInt(3, salas.getId_sala());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM salas WHERE id_sala = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Salas> findAll() throws SQLException {
        List<Salas> salas = new ArrayList<>();
        String sql = "SELECT * FROM salas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Salas sala = new Salas(rs.getInt("id_sala"), rs.getString("nome"), rs.getInt("id_predio"));
                salas.add(sala);
            }
        }
        return salas;
    }

    public Salas findById(int id) throws SQLException {
        String sql = "SELECT * FROM sala WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Salas(rs.getInt("id_sala"), rs.getString("nome"), rs.getInt("id_predio"));
                }
            }
        }
        return null;
    }
}
