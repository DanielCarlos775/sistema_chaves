package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Predio;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PredioDAO {

    // INSERT
    public void insert (Predio predio) {
        String sql = "INSERT INTO predios (nome, descricao, ativo) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, predio.getNome());
            stmt.setString(2, predio.getDescricao());
            stmt.setBoolean(3, predio.isAtivo());
            stmt.executeUpdate();

            System.out.println("Predio Cadastrado com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR SALAS
    public List<Predio> findAll() {
        List<Predio> predios = new ArrayList<>();
        String sql = "SELECT * FROM salas";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Predio predio = new Predio();
                predio.setIdPredio(rs.getInt("id_predio"));
                predio.setNome(rs.getString("nome"));
                predio.setDescricao(rs.getString("descricao"));
                predio.setAtivo(rs.getBoolean("ativo"));
                predio.setDataCriacao(rs.getTimestamp("data_criacao"));
                predio.setDataAtualizacao(rs.getTimestamp("data_atualizacao"));
                predios.add(predio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return predios;
    }

}
