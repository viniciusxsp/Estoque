package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaUsuario extends JFrame {
    private JTextField txtId, txtLogin;
    private JPasswordField txtSenha;
    private JButton btnSalvar, btnExcluir, btnLimpar;
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;

    private final Color COR_FUNDO = new Color(248, 249, 250);       
    private final Color COR_CARD = new Color(255, 255, 255);        
    private final Color COR_TEXTO_ESCURO = new Color(43, 58, 66);   
    private final Color LARANJA_PRINCIPAL = new Color(230, 126, 34); 
    private final Color LARANJA_ESCURO = new Color(211, 84, 0);      
    private final Color LARANJA_SUAVE = new Color(253, 242, 233);   
    private final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);

    public TelaUsuario() {
        setTitle("Controle de Usuários e Autenticação");
        setSize(850, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(20, 20));

        // --- TOPO ---
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setBackground(LARANJA_ESCURO);
        painelHeader.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        JLabel lblTituloTela = new JLabel("Configurações do Sistema > Usuários");
        lblTituloTela.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTituloTela.setForeground(Color.WHITE);
        painelHeader.add(lblTituloTela, BorderLayout.WEST);
        add(painelHeader, BorderLayout.NORTH);

        // --- CENTRO ---
        JPanel painelCentral = new JPanel(new BorderLayout(0, 20));
        painelCentral.setBackground(COR_FUNDO);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBackground(COR_CARD);
        painelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 235, 240), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        adicionarCampo(painelForm, "Código/ID:", txtId = new JTextField(), gbc, 0);
        adicionarCampo(painelForm, "Login de Acesso:", txtLogin = new JTextField(), gbc, 1);
        
        JLabel lblSenha = new JLabel("Senha de Acesso:");
        lblSenha.setFont(FONTE_PADRAO); lblSenha.setForeground(COR_TEXTO_ESCURO);
        txtSenha = new JPasswordField();
        txtSenha.setFont(FONTE_PADRAO); txtSenha.setPreferredSize(new Dimension(250, 32));
        txtSenha.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(215, 220, 225), 1), BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.2; painelForm.add(lblSenha, gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.8; painelForm.add(txtSenha, gbc);

        painelCentral.add(painelForm, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"ID", "Login/Usuário", "Senha (Mascarada)"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaUsuarios = new JTable(modeloTabela);
        configurarEstiloTabela();
        
        modeloTabela.addRow(new Object[]{"1", "admin", "••••••••"});

        JScrollPane scrollTabela = new JScrollPane(tabelaUsuarios);
        scrollTabela.getViewport().setBackground(COR_CARD);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(230, 235, 240)));
        painelCentral.add(scrollTabela, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);

        // --- INFERIOR ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        btnLimpar = criarBotaoBorda("Limpar");
        btnExcluir = criarBotaoColorido("Excluir", new Color(217, 83, 79));
        btnSalvar = criarBotaoColorido("Salvar Usuário", LARANJA_PRINCIPAL);

        painelBotoes.add(btnLimpar); painelBotoes.add(btnExcluir); painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações
        btnSalvar.addActionListener(e -> {
            if (txtId.getText().trim().isEmpty() || txtLogin.getText().trim().isEmpty() || new String(txtSenha.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            modeloTabela.addRow(new Object[]{txtId.getText(), txtLogin.getText(), "••••••••"});
            JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        });

        btnLimpar.addActionListener(e -> limparCampos());

        btnExcluir.addActionListener(e -> {
            int linha = tabelaUsuarios.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            modeloTabela.removeRow(linha);
            limparCampos();
        });

        tabelaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaUsuarios.getSelectedRow();
                if (linha != -1) {
                    txtId.setText(modeloTabela.getValueAt(linha, 0).toString());
                    txtLogin.setText(modeloTabela.getValueAt(linha, 1).toString());
                    txtSenha.setText(""); // Por segurança não puxa a senha antiga limpa
                }
            }
        });
    }

    private void limparCampos() {
        txtId.setText(""); txtLogin.setText(""); txtSenha.setText(""); tabelaUsuarios.clearSelection();
    }

    private void adicionarCampo(JPanel painel, String labelText, JTextField textField, GridBagConstraints gbc, int linha) {
        JLabel label = new JLabel(labelText); label.setFont(FONTE_PADRAO); label.setForeground(COR_TEXTO_ESCURO);
        textField.setFont(FONTE_PADRAO); textField.setPreferredSize(new Dimension(250, 32));
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(215, 220, 225), 1), BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        gbc.gridx = 0; gbc.gridy = linha; gbc.weightx = 0.2; painel.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.weightx = 0.8; painel.add(textField, gbc);
    }

    private void configurarEstiloTabela() {
        tabelaUsuarios.setFont(FONTE_PADRAO); tabelaUsuarios.setRowHeight(35); tabelaUsuarios.setGridColor(new Color(240, 242, 245));
        tabelaUsuarios.setSelectionBackground(LARANJA_SUAVE); tabelaUsuarios.setSelectionForeground(COR_TEXTO_ESCURO);
        JTableHeader header = tabelaUsuarios.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); header.setBackground(COR_TEXTO_ESCURO);
        header.setForeground(Color.WHITE); header.setPreferredSize(new Dimension(100, 38));
    }

    private JButton criarBotaoColorido(String texto, Color corFundo) {
        JButton b = new JButton(texto); b.setFont(new Font("Segoe UI", Font.BOLD, 14)); b.setForeground(Color.WHITE);
        b.setBackground(corFundo); b.setFocusPainted(false); b.setBorderPainted(false); b.setPreferredSize(new Dimension(150, 38)); b.setCursor(new Cursor(Cursor.HAND_CURSOR)); return b;
    }

    private JButton criarBotaoBorda(String texto) {
        JButton b = new JButton(texto); b.setFont(FONTE_PADRAO); b.setForeground(COR_TEXTO_ESCURO); b.setBackground(Color.WHITE);
        b.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 211))); b.setFocusPainted(false); b.setPreferredSize(new Dimension(100, 38)); b.setCursor(new Cursor(Cursor.HAND_CURSOR)); return b;
    }
}