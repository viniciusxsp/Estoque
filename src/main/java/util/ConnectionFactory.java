package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String URL =
            "jdbc:mysql://localhost:3306/estoque_erp";

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro na conexão", e);
        }
    }
}