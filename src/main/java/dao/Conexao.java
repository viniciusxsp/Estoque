import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));

            String url = prop.getProperty("db.url");
            String usuario = prop.getProperty("db.user");
            String senha = prop.getProperty("db.password");

            return DriverManager.getConnection(url, usuario, senha);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}