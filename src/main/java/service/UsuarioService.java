package service;

import model.Usuario;

public class UsuarioService {

    public boolean autenticar(Usuario usuario) {

        return usuario.getLogin().equals("admin")
            && usuario.getSenha().equals("1234");
    }
}