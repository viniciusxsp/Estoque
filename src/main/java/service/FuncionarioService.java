package service;

import model.Funcionario;

public class FuncionarioService {

    public void cadastrar(Funcionario funcionario) {

        System.out.println(
                "Funcionário cadastrado: "
                + funcionario.getNome());
    }
}