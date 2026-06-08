package controller;

import java.util.ArrayList;
import model.Produto;

public class ProdutoController {

    private ArrayList<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public ArrayList<Produto> listar() {
        return produtos;
    }
}