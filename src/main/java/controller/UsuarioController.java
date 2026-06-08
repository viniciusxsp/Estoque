package controller;

import java.util.ArrayList;
import model.Usuario;

public class UsuarioController {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public ArrayList<Usuario> listar() {
        return usuarios;
    }
}