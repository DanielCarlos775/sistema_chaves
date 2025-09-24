package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    // Construtor para injeção de conexão (usado em testes)
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Construtor padrão (usa o ConnectionFactory)
    public UsuarioDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
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

    // =======================
    // FINDERS
    // =======================

    // Buscar usuário por ID
    public Usuario findById(int id) throws SQLException {
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

    // Buscar usuário pelo Email
    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapUsuario(rs);
                }
            }
        }
        return null;
    }

    // Listar todos
    public List<Usuario> list() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
               usuarios.add(mapUsuario(rs));
            }
        }
        return usuarios;
    }

    // =======================
    // UPDATES
    // =======================

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

    // =======================
    // MAPPER
    // =======================
    //Metodo auxiliar para mapear ResultSet -> Usuario
    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id_usuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipoUsuario(rs.getString("tipo_usuario"));
        return usuario;
    }
}

