package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {

        try {

            String url =
            "jdbc:mysql://localhost:3306/Supermercado_Acacias";

            String usuario = "root";
            String senha = "";

            return DriverManager.getConnection(
                    url,
                    usuario,
                    senha);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        }
    }
}