package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Produto;

public class ProdutoDAO {

    public void salvar(Produto produto) {

        String sql =
                "INSERT INTO produto "
                + "(codigo,nome,quantidade,preco) "
                + "VALUES (?,?,?,?)";

        try {

            Connection con =
                    Conexao.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,
                    produto.getCodigo());

            ps.setString(2,
                    produto.getNome());

            ps.setInt(3,
                    produto.getQuantidade());

            ps.setDouble(4,
                    produto.getPreco());

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}