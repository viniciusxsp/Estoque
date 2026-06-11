package view;

import javax.swing.*;
import java.awt.*;

public class PrincipalFrame extends JFrame {

    public PrincipalFrame() {
        setTitle("Sistema de Estoque - Menu Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Criação da barra de menu no topo
        JMenuBar barraMenu = new JMenuBar();

        // 2. Criação do menu principal "Cadastros"
        JMenu menuCadastros = new JMenu("Cadastros");
        
        // 3. Criação das opções (itens) do menu
        JMenuItem itemProdutos = new JMenuItem("Produtos");
        JMenuItem itemFuncionarios = new JMenuItem("Funcionários");
        JMenuItem itemUsuarios = new JMenuItem("Usuários");
        JMenuItem itemSair = new JMenuItem("Sair");

        // Coloca as opções dentro do menu "Cadastros"
        menuCadastros.add(itemProdutos);
        menuCadastros.add(itemFuncionarios);
        menuCadastros.add(itemUsuarios);
        menuCadastros.addSeparator(); // Linha divisória visual
        menuCadastros.add(itemSair);

        // Adiciona o menu completo na barra do topo
        barraMenu.add(menuCadastros);
        setJMenuBar(barraMenu);

        // 4. Painel de fundo com a mensagem de boas-vindas
        JPanel painelFundo = new JPanel(new BorderLayout());
        JLabel lblBoasVindas = new JLabel("Bem-vindo ao Sistema de Estoque", SwingConstants.CENTER);
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 24));
        painelFundo.add(lblBoasVindas, BorderLayout.CENTER);
        add(painelFundo);

        // ==========================================
        // 5. AÇÕES DOS MENUS (O que acontece ao clicar)
        // ==========================================
        
        // Quando clicar em "Produtos", abre a TelaProduto
        itemProdutos.addActionListener(e -> {
            new TelaProduto().setVisible(true);
        });

        // Quando clicar em "Funcionários", abre a TelaFuncionario
        itemFuncionarios.addActionListener(e -> {
            new TelaFuncionario().setVisible(true);
        });

        // Quando clicar em "Usuários", abre a TelaUsuario
        itemUsuarios.addActionListener(e -> {
            new TelaUsuario().setVisible(true);
        });

        // Quando clicar em "Sair", fecha o sistema todo
        itemSair.addActionListener(e -> System.exit(0));
    }
}