package view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Área de Autenticação");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Painel Principal com Layout de Caixa (Box) para centralizar
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Título estilizado na tela
        JLabel lblTitulo = new JLabel("LOGIN - ESTOQUE");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento

        // Campo Usuário
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(300, 30));
        painelPrincipal.add(txtUsuario);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campo Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(lblSenha);
        
        txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(300, 30));
        painelPrincipal.add(txtSenha);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botão Entrar
        btnLogin = new JButton("Entrar no Sistema");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(new Color(50, 150, 50));
        btnLogin.setForeground(Color.WHITE);
        painelPrincipal.add(btnLogin);

        add(painelPrincipal);

        // Ação do Botão
        btnLogin.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtSenha.getPassword());
            
            if(user.equals("admin") && pass.equals("1234")) {
                new PrincipalFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Acesso Negado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}