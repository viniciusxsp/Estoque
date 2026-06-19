package service;

import model.Usuario;

public class UsuarioService {

    public boolean autenticar(
            String login,
            String senha) {

        return login.equals("admin")
                && senha.equals("1234");
    }

    public void cadastrar(Usuario usuario) {

        System.out.println(
                "Usuário cadastrado: "
                + usuario.getLogin());
    }
}