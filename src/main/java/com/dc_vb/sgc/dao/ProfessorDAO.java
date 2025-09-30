package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Professor;
import com.dc_vb.sgc.model.Sala;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    // INSERT
    public void insert(Professor professor) {
        String sql = "INSERT INTO professores (nome, email, telefone, cpf, ativo) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getTelefone());
            stmt.setString(4, professor.getCpf());
            stmt.setBoolean(5, professor.isAtivo());
            stmt.executeUpdate();

            System.out.println("Professor Cadastrado com Sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR PROFESSORES
    public List<Professor> findAll() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setIdProfessor(rs.getInt("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setCpf(rs.getString("cpf"));
                professor.setAtivo(rs.getBoolean("ativo"));
                professor.setDataCriacao(rs.getTimestamp("data_criacao"));
                professor.setDataAtualizacao(rs.getTimestamp("data_atualizacao"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }

}
