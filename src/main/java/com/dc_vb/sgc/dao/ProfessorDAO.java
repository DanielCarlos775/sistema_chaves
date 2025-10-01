package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Professor;
import com.dc_vb.sgc.model.Sala;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    // INSERT
    public void insert(Professor professor, int idCoordenador) {
        String sqlProfessor = "INSERT INTO professores (nome, email, telefone, cpf, ativo) VALUES (?, ?, ?, ?, ?)";
        String sqlVinculo = "INSERT INTO coordenador_professor (id_coordenador, id_professor, ativo) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtVinculo = conn.prepareStatement(sqlVinculo)) {

            // Inserir Professor
            stmtProfessor.setString(1, professor.getNome());
            stmtProfessor.setString(2, professor.getEmail());
            stmtProfessor.setString(3, professor.getTelefone());
            stmtProfessor.setString(4, professor.getCpf());
            stmtProfessor.setBoolean(5, professor.isAtivo());
            stmtProfessor.executeUpdate();

            // Recuperar id gerado
            ResultSet rs = stmtProfessor.getGeneratedKeys();
            int idProfessor = 0;
            if (rs.next()) {
                idProfessor = rs.getInt(1);
            }

            // Inserir vínculo com o coordenador
            stmtVinculo.setInt(1, idCoordenador);
            stmtVinculo.setInt(2, idProfessor);
            stmtVinculo.setBoolean(3, true);
            stmtVinculo.executeUpdate();

            System.out.println("Professor Cadastrado e vinculado ao Coordenador com Sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // LISTAR PROFESSORES
    public List<Professor> findAll() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT p.* FROM professores p"
                + "JOIN coordenador_professor cp ON p.id_professor = cp.id_professor "
                + "WHERE cp.id_coordenador = ? AND cp.ativo = TRUE";

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
