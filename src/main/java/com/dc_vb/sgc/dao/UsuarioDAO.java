package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // INSERT
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha()); // ideal: hash
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.executeUpdate();
        }
    }

    // FIND BY EMAIL (para verificar se já existe no cadastro)
    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                    return usuario;
                }
            }
        }
        return null;
    }

    // FIND BY EMAIL + SENHA (autenticação de login)
    public Usuario findByEmailAndSenha(String email, String senha) throws SQLException {
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha); // OBS: em produção, compare hash da senha
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                    return usuario;
                }
            }
        }
        return null; // login inválido
    }

    // LIST ALL
    public List<Usuario> list() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    // UPDATE
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, tipo_usuario = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        }
    }
}
