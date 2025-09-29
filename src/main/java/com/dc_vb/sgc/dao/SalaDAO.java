package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Sala;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    // INSERT
    public void insert(Sala sala) {
        String sql = "INSERT INTO salas (id_predio, nome, descricao, capacidade, ativo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sala.getIdPredio());
            stmt.setString(2, sala.getNome());
            stmt.setString(3, sala.getDescricao());
            stmt.setInt(4, sala.getCapacidade());
            stmt.setBoolean(5, sala.isAtivo());
            stmt.executeUpdate();

            System.out.println("Sala Cadastrada com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR SALAS
    public List<Sala> findAll() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM salas";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Sala sala = new Sala();
                sala.setIdSala(rs.getInt("id_sala"));
                sala.setIdPredio(rs.getInt("id_predio"));
                sala.setNome(rs.getString("nome"));
                sala.setDescricao(rs.getString("descricao"));
                sala.setCapacidade(rs.getInt("capacidade"));
                sala.setAtivo(rs.getBoolean("ativo"));
                sala.setDataCriacao(rs.getTimestamp("data_criacao"));
                sala.setDataAtualizacao(rs.getTimestamp("data_atualizacao"));
                salas.add(sala);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }


/*    public void update(Salas salas) throws SQLException {
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
    }*/
}
