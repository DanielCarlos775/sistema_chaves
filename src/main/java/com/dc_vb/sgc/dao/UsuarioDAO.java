package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERT
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo_usuario, id_predio, id_sala, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha()); // ideal: hash
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setInt(5, usuario.getIdPredio());
            stmt.setInt(6, usuario.getIdSala());
            stmt.setBoolean(7, usuario.isAtivo());
            stmt.executeUpdate();

            System.out.println("Usuário inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ALTERAR
    // Atualizar dados gerais (menos senha)
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, tipo_usuario = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        }
    }

    // Atualizar somente a senha
    public boolean updateSenha(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET senha = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getSenha());
            stmt.setInt(2, usuario.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }


    // LISTAR TODOS OS USUARIOS
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                usuario.setIdPredio(rs.getInt("id_predio"));
                usuario.setIdSala(rs.getInt("id_sala"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao"));
                usuario.setDataAtualizacao(rs.getTimestamp("data_atualizacao"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Buscar usuário pelo Email
    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios WHERE email = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                            usuario.setIdUsuario(rs.getInt("id_usuario"));
                            usuario.setNome(rs.getString("nome"));
                            usuario.setEmail(rs.getString("email"));
                            usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                            return usuario;
                }
            }
        }
        return null;
    }

    //Buscar Usuário pelo Email e Senha
    public Usuario findByEmailSenha(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                        usuario.setEmail(rs.getString("email"));
                        usuario.setSenha(rs.getString("senha"));
                    return usuario;
                }
            }
        }
        return null;
    }

    // Buscar usuário por ID
    /*public Usuario findById(int id) throws SQLException {
        String sql = "SELECT id_usuario nome, email, senha, tipo_usuario FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapUsuario(rs);
                }
            }
        }
        return null;
    }



    // =======================
    // DELETE
    // =======================
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    }*/
}

