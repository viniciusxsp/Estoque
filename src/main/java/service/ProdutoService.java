package service;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoService {

    private ProdutoDAO dao = new ProdutoDAO();

    public void salvar(Produto produto) {

        dao.cadastrar(produto);
    }
}