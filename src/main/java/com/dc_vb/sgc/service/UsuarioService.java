package com.dc_vb.sgc.service;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;

import java.sql.SQLException;
import java.util.Objects;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario cadastrarUsuario(Usuario u) throws SQLException {
        validarEntradaBasica(u);

        // Email único
        if (usuarioDAO.findByEmail(u.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        // Defaults de negócio para o cadastro
        u.setAtivo(true);
        u.setSenhaAlterada(false);

        usuarioDAO.insert(u);
        return u; // Já vem com idUsuario preenchido
    }

    private void validarEntradaBasica(Usuario u) {
        if (u == null) throw new IllegalArgumentException("Usuario não informado.");

        if (isBlank(u.getNome())) throw new IllegalArgumentException("Nome é obrigatório.");
        if (isBlank(u.getEmail())) throw new IllegalArgumentException("Email é obrigatório.");
        if (isBlank(u.getSenha())) throw new IllegalArgumentException("Senha é obrigatória.");
        if (isBlank(u.getTipoUsuario()))
            throw new IllegalArgumentException("Tipo de usuário é obrigatório.");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
