package com.dc_vb.sgc.service;

import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.dao.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class AuthService {

    private final UsuarioDAO usuarioDAO;

    public AuthService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Realiza login validando email e senha com BCrypt.
     * @param email Email do usuário
     * @param senha Senha digitada (texto puro)
     * @return Usuario se login for válido, ou null se inválido
     */
    public Usuario login(String email, String senha) throws SQLException {
        Usuario user = usuarioDAO.findByEmail(email);

        if (user != null && BCrypt.checkpw(senha, user.getSenha())) {
            return user; // Login OK
        }
        return null; // login ou senha inválidos
    }

    /**
     * Cria um novo usuário com senha já criptografada.
     * @param usuario Objeto usuario com senha em texto puro
     */
    public void registrarUsuario(Usuario usuario) throws SQLException {
        // Gera o hash da senha antes de salvar
        String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(senhaHash);

        usuarioDAO.insert(usuario);
    }

    /**
     * Altera a senha de um usuário existente, validando a senha atual.
     * @param usuarioId ID do usuário
     * @param senhaAtual Senha atual em texto puro
     * @param novaSenha Nova senha em texto puro
     * @return true se a alteração for bem-sucedida, false caso contrário
     */
    public boolean changePassword(int usuarioId, String senhaAtual, String novaSenha) throws SQLException {
        Usuario user = usuarioDAO.findById(usuarioId);

        if (user != null && BCrypt.checkpw(senhaAtual, user.getSenha())) {
            String novaSenhaHash = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
            user.setSenha(novaSenhaHash);
            return usuarioDAO.updateSenha(user);
        }
        return false; // senha atual incorreta ou usuário não existe
    }
}
