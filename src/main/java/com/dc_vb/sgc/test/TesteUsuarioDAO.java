package com.dc_vb.sgc.test;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;

public class TesteUsuarioDAO {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sistema_chaves";
        String user = "root";
        String password = "daniel123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

            // 1. Cadastrar usuário
            Usuario novo = new Usuario();
            novo.setNome("Daniel Silva");
            novo.setEmail("daniel4@email.com");
            novo.setSenha("123456"); // atenção: senha em texto puro
            novo.setTipoUsuario("ADM");
            usuarioDAO.insert(novo);
            System.out.println("Usuário cadastrado com sucesso!");

            // 2. Testar login
            Usuario logado = usuarioDAO.findByEmailAndSenha("daniel4@email.com", "123456");
            if (logado != null) {
                System.out.println("Login OK: " + logado.getNome() + " | Tipo: " + logado.getTipoUsuario());
            } else {
                System.out.println("Email ou senha inválidos!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
