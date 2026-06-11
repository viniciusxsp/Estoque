package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProdutoDAO {

    public void cadastrar(String nome,
                          int quantidade,
                          double preco) {

        String sql =
            "INSERT INTO produtos(nome, quantidade, preco) VALUES (?, ?, ?)";

        try {
            Connection con = Conexao.conectar();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nome);
            ps.setInt(2, quantidade);
            ps.setDouble(3, preco);

            ps.executeUpdate();

            System.out.println("Produto cadastrado!");

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
