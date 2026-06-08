package view;

import javax.swing.*;

public class PrincipalFrame extends JFrame {

    public PrincipalFrame() {

        setTitle(
                "Sistema de Estoque");

        setSize(
                800,
                600);

        setLocationRelativeTo(
                null);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE);

        JMenuBar barra =
                new JMenuBar();

        JMenu menuCadastro =
                new JMenu("Cadastros");

        JMenuItem produto =
                new JMenuItem("Produtos");

        JMenuItem funcionario =
                new JMenuItem("Funcionários");

        menuCadastro.add(produto);
        menuCadastro.add(funcionario);

        barra.add(menuCadastro);

        setJMenuBar(barra);

        produto.addActionListener(e ->
                new TelaProduto()
                        .setVisible(true));

        funcionario.addActionListener(e ->
                new TelaFuncionario()
                        .setVisible(true));
    }
}