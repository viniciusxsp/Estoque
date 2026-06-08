package util;

import model.Usuario;

public class SessaoUsuario {

    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(
            Usuario usuario) {

        usuarioLogado = usuario;
    }

    public static void limpar() {
        usuarioLogado = null;
    }
}