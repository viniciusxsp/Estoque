package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/estoque";

    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    SENHA
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro na conexão: "
                    + e.getMessage()
            );

            return null;
        }
    }
}