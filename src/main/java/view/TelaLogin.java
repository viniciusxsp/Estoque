package view;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnSair;
    
    // CONTADOR DE SEGURANÇA
    private int tentativas = 0; 

    public TelaLogin() {
        setTitle("Autenticação - Sistema de Estoque");
        setSize(380, 240);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel painel = new JPanel(new GridLayout(3, 2, 12, 12));
        painel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        painel.setBackground(Color.WHITE);

        painel.add(new JLabel("Usuário / Login:"));
        txtUsuario = new JTextField();
        painel.add(txtUsuario);

        painel.add(new JLabel("Senha de Acesso:"));
        txtSenha = new JPasswordField();
        painel.add(txtSenha);

        btnSair = new JButton("Sair");
        btnEntrar = new JButton("Entrar");
        
        // Estilo rápido nos botões do login
        btnEntrar.setBackground(new Color(230, 126, 34));
        btnEntrar.setForeground(Color.WHITE);

        painel.add(btnSair);
        painel.add(btnEntrar);
        add(painel);

        btnSair.addActionListener(e -> System.exit(0));

        // LÓGICA DE 3 TENTATIVAS
        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            // Usuário padrão de teste do sistema
            if (usuario.equals("admin") && senha.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Acesso Autorizado! Bem-vindo.");
                new PrincipalFrame().setVisible(true);
                this.dispose(); // Fecha tela de login
            } else {
                tentativas++; // Incrementa erro
                int restantes = 3 - tentativas;

                if (tentativas >= 3) {
                    JOptionPane.showMessageDialog(this, "Acesso Bloqueado! Você excedeu as 3 tentativas permitidas.", "Segurança", JOptionPane.ERROR_MESSAGE);
                    btnEntrar.setEnabled(false); // Desativa o botão permanentemente
                    txtUsuario.setEnabled(false);
                    txtSenha.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos! \nTentativas restantes: " + restantes, "Falha na Autenticação", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}