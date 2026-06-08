package controller;

import java.util.ArrayList;
import model.Funcionario;

public class FuncionarioController {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public ArrayList<Funcionario> listar() {
        return funcionarios;
    }
}