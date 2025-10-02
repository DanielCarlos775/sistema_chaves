package com.dc_vb.sgc.dao;

import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.util.ConnectionFactory;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERT
    public void insert(Usuario u) throws SQLException {
        final String sql = "INSERT INTO usuarios " +
        "(nome, email, senha, tipo_usuario, id_predio, id_sala, ativo, senha_alterada) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha()); // ideal: hash
            stmt.setString(4, u.getTipoUsuario());

            if (u.getIdPredio() == null) stmt.setNull(5, Types.INTEGER);
            else stmt.setInt(5, u.getIdPredio());

            if (u.getIdSala() == null) stmt.setNull(6, Types.INTEGER);
            else stmt.setInt(6, u.getIdSala());

            stmt.setBoolean(7, u.isAtivo());
            stmt.setBoolean(8, u.isSenhaAlterada());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setIdUsuario(rs.getInt(1));
                }
            }
        }
    }

    // ALTERAR
    // Atualizar dados gerais (menos senha)
    public boolean updateUsuario(Usuario usuario, String tipoUsuarioLogado) throws SQLException {
        String sql;
        boolean isADM = "ADM".equalsIgnoreCase(tipoUsuarioLogado);

        if (isADM) {
            sql = "UPDATE usuarios SET nome = ?, email = ?, tipo_usuario = ?, "
                    + "id_predio = ?, id_sala = ?, ativo = ?, data_atualizacao = CURRENT_TIMESTAMP "
                    + "WHERE id_usuario = ?";
        } else {
            sql = "UPDATE usuarios SET nome = ?, email = ?, id_predio = ?, id_sala = ?, "
                    + "ativo = ?, data_atualizacao = CURRENT_TIMESTAMP "
                    + "WHERE id_usuario = ?";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int idx = 1;

            // Campos Comuns
            stmt.setString(idx++, usuario.getNome());
            stmt.setString(idx++, usuario.getEmail());

            if (isADM) {
                stmt.setString(idx++, usuario.getTipoUsuario()); //Apenas ADM pode alterar
            }

            // Se for Administrativo, manten os IDs. Se não for, seta NULL.
            if ("Administrativo".equalsIgnoreCase(usuario.getTipoUsuario())) {
                stmt.setObject(idx++, usuario.getIdPredio(), Types.INTEGER);
                stmt.setObject(idx++, usuario.getIdSala(), Types.INTEGER);
            } else {
                stmt.setNull(idx++, Types.INTEGER);
                stmt.setNull(idx++, Types.INTEGER);
            }

            stmt.setBoolean(idx++, usuario.isAtivo());

            // id_usuario (PK)
            stmt.setInt(idx++, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;
        }
    }

    // Atualizar somente a senha
    public boolean updateSenha(int idUsuario, String novaSenha) throws SQLException {
        String sql = "UPDATE usuarios SET senha = ?, senha_alterada = TRUE, "
                + "data_atualizacao = CURRENT_TIMESTAMP "
                + "WHERE id_usuario = ? AND senha_alterada = FALSE";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaSenha);
            stmt.setInt(2, idUsuario);

            // Retorna true somente se conseguir atualizar
            return stmt.executeUpdate() > 0;
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
        //        usuario.setDataCriacao(rs.getTimestamp("data_criacao"));
        //        usuario.setDataAtualizacao(rs.getTimestamp("data_atualizacao"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Buscar usuário pelo Nome
    public Usuario findByNome(String nome) throws SQLException {
        String sql = "SELECT id_usuario, nome, email, senha, tipo_usuario FROM usuarios WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

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

    // Buscar usuário pelo Email
    public Usuario findByEmail(String email) throws SQLException {
        final String sql =
                "SELECT id_usuario, nome, email, senha, tipo_usuario, id_predio, id_sala, " +
                "ativo, senha_alterada, data_criacao, data_atualizacao " +
                "FROM usuarios " +
                "WHERE LOWER(email) = LOWER(?) LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setSenha(rs.getString("senha"));
                    u.setTipoUsuario(rs.getString("tipo_usuario"));

                    u.setIdPredio(rs.getObject("id_predio", Integer.class));
                    u.setIdSala(rs.getObject("id_sala", Integer.class));

                    u.setAtivo(rs.getBoolean("ativo"));
                    u.setSenhaAlterada(rs.getBoolean("senha_alterada"));

                    Timestamp dc = rs.getTimestamp("data_criacao");
                    if (dc != null) {
                        u.setDataCriacao(dc.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                }
                    Timestamp da = rs.getTimestamp("data_atualizacao");
                    if (da != null) {
                        u.setDataAtualizacao(da.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    }
                    return u;
            }
        }
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

    // =======================
    // DELETE
    // =======================
/*    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }*/
}


