package dao;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        Connection con = Conexao.conectar();

        if (con != null) {
            System.out.println("Conexao realizada com sucesso!");
        } else {
            System.out.println("Erro ao conectar.");
        }

    }
}