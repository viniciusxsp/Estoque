
package dao;

/**
 *
 * @author vinic
 */
import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        Connection con = Conexao.conectar();

        if (con != null) {
            System.out.println("Conectado com sucesso!");
        } else {
            System.out.println("Falha na conexão!");
        }
    }
}