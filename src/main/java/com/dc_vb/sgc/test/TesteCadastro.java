package com.dc_vb.sgc.test;

import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.service.UsuarioService;

public class TesteCadastro {

    public static void main(String[] args) {
        try {
            UsuarioService service = new UsuarioService();

            Usuario novo = new Usuario(
                    "Maria Souza",
                    "maria.souza@example.com",
                    "123456",
                    "COORDENADOR",
                    null,
                    null
            );

            service.cadastrarUsuario(novo);
            System.out.println("Cadastrado com ID: " + novo.getIdUsuario());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
