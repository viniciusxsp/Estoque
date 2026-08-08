package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Produto;

public class ProdutoDAO {

    public void cadastrar(Produto p) {

        String sql =
        "INSERT INTO PRODUTO " +
        "(NOME_PRODUTO, TIPO_PRODUTO, MARCA_PRODUTO, " +
        "QUANTIDADE_ESTOQUE, VALOR_PRODUTO, VALIDADE_PRODUTO) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con = Conexao.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, p.getNome());
            ps.setString(2, p.getTipo());
            ps.setString(3, p.getMarca());
            ps.setInt(4, p.getQuantidade());
            ps.setDouble(5, p.getPreco());
            ps.setString(6, p.getValidade());

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Produto> listar() {

        ArrayList<Produto> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM PRODUTO";

        try {

            Connection con = Conexao.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Produto p = new Produto();

                p.setId(
                    rs.getInt("ID_PRODUTO"));

                p.setNome(
                    rs.getString("NOME_PRODUTO"));

                p.setTipo(
                    rs.getString("TIPO_PRODUTO"));

                p.setMarca(
                    rs.getString("MARCA_PRODUTO"));

                p.setQuantidade(
                    rs.getInt("QUANTIDADE_ESTOQUE"));

                p.setPreco(
                    rs.getDouble("VALOR_PRODUTO"));

                p.setValidade(
                    rs.getString("VALIDADE_PRODUTO"));

                lista.add(p);
            }

            rs.close();
            ps.close();
            con.close();

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void excluir(int id) {

        String sql =
                "DELETE FROM PRODUTO WHERE ID_PRODUTO=?";

        try {

            Connection con = Conexao.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void cadastrar(String nome, String tipo, String marca, int quantidade, double preco, String validade) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}