package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Chave;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChaveDAO {

    // INSERT
    public void insert(Chave chave) {
        String sql = "INSERT INTO chaves (id_sala, codigo, descricao, ativo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, chave.getIdSala());
            stmt.setString(2, chave.getCodigo());
            stmt.setString(3, chave.getDescricao());
            stmt.setBoolean(4, chave.isAtivo());
            stmt.executeUpdate();

            System.out.println("Chave Cadastrada com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR CHAVES
    public List<Chave> findAll() {
        List<Chave> chaves = new ArrayList<>();
        String sql = "SELECT * FROM chaves";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Chave chave = new Chave();
                chave.setIdChave(rs.getInt("id_chave"));
                chave.setIdSala(rs.getInt("id_sala"));
                chave.setCodigo(rs.getString("codigo"));
                chave.setDescricao(rs.getString("descricao"));
                chave.setAtivo(rs.getBoolean("ativo"));
                chave.setDataCriacao(rs.getTimestamp("data_criacao"));
                chave.setDataAtualizacao(rs.getTimestamp("data_atualizaca"));
                chaves.add(chave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chaves;
    }

/*    public void update(Chaves chave) throws SQLException {
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
    }*/
}
