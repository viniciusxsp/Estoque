package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaFuncionario extends JFrame {
    private JTextField txtId, txtNome, txtCpf;
    private JComboBox<String> cbCargo;
    private JButton btnSalvar, btnExcluir, btnLimpar;
    private JTable tabelaFuncionarios;
    private DefaultTableModel modeloTabela;

    // Paleta Laranja e Branco
    private final Color COR_FUNDO = new Color(248, 249, 250);       
    private final Color COR_CARD = new Color(255, 255, 255);        
    private final Color COR_TEXTO_ESCURO = new Color(43, 58, 66);   
    private final Color LARANJA_PRINCIPAL = new Color(230, 126, 34); 
    private final Color LARANJA_ESCURO = new Color(211, 84, 0);      
    private final Color LARANJA_SUAVE = new Color(253, 242, 233);   
    private final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);

    public TelaFuncionario() {
        setTitle("Gerenciamento de Funcionários");
        setSize(850, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(20, 20));

        // --- TOPO: CABEÇALHO ---
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setBackground(LARANJA_ESCURO);
        painelHeader.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        JLabel lblTituloTela = new JLabel("Controle de Equipe > Funcionários");
        lblTituloTela.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTituloTela.setForeground(Color.WHITE);
        painelHeader.add(lblTituloTela, BorderLayout.WEST);
        add(painelHeader, BorderLayout.NORTH);

        // --- CENTRO: FORMULÁRIO + TABELA ---
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
        adicionarCampo(painelForm, "Nome Completo:", txtNome = new JTextField(), gbc, 1);
        adicionarCampo(painelForm, "CPF:", txtCpf = new JTextField(), gbc, 2);

        // Seletor de Cargos solicitado
        JLabel lblCargo = new JLabel("Cargo na Empresa:");
        lblCargo.setFont(FONTE_PADRAO);
        lblCargo.setForeground(COR_TEXTO_ESCURO);
        String[] cargos = {"Admin", "Supervisor", "Operador de Caixa"};
        cbCargo = new JComboBox<>(cargos);
        cbCargo.setFont(FONTE_PADRAO);
        cbCargo.setPreferredSize(new Dimension(250, 32));
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.2;
        painelForm.add(lblCargo, gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.8;
        painelForm.add(cbCargo, gbc);

        painelCentral.add(painelForm, BorderLayout.NORTH);

        // Tabela de Funcionários (Inclui coluna de Limite de Acesso)
        String[] colunas = {"ID", "Nome", "CPF", "Cargo", "Nível de Acesso"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaFuncionarios = new JTable(modeloTabela);
        configurarEstiloTabela();
        
        // Dados fictícios para teste
        modeloTabela.addRow(new Object[]{"101", "Carlos Silva", "123.456.789-10", "Admin", "Total (Root)"});
        modeloTabela.addRow(new Object[]{"102", "Ana Souza", "987.654.321-20", "Operador de Caixa", "Apenas Vendas/Estoque"});

        JScrollPane scrollTabela = new JScrollPane(tabelaFuncionarios);
        scrollTabela.getViewport().setBackground(COR_CARD);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(230, 235, 240)));
        painelCentral.add(scrollTabela, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);

        // --- INFERIOR: BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        btnLimpar = criarBotaoBorda("Limpar");
        btnExcluir = criarBotaoColorido("Excluir", new Color(217, 83, 79)); 
        btnSalvar = criarBotaoColorido("Salvar Cadastro", LARANJA_PRINCIPAL); 

        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);

        // ==========================================
        // FUNCIONALIDADES DOS BOTÕES
        // ==========================================

        btnSalvar.addActionListener(e -> {
            if (txtId.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha o Código e o Nome do Funcionário.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String id = txtId.getText();
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String cargo = cbCargo.getSelectedItem().toString();
            String nivelAcesso = "";

            // Lógica de limite de acesso baseada no cargo
            switch (cargo) {
                case "Admin":
                    nivelAcesso = "Total (Root)";
                    break;
                case "Supervisor":
                    nivelAcesso = "Gerencial (Sem excluir)";
                    break;
                case "Operador de Caixa":
                    nivelAcesso = "Apenas Vendas/Estoque";
                    break;
            }

            modeloTabela.addRow(new Object[]{id, nome, cpf, cargo, nivelAcesso});
            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com o nível: " + nivelAcesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        });

        btnLimpar.addActionListener(e -> limparCampos());

        btnExcluir.addActionListener(e -> {
            int linha = tabelaFuncionarios.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um funcionário na tabela.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Deseja excluir este funcionário?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modeloTabela.removeRow(linha);
                limparCampos();
            }
        });

        tabelaFuncionarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaFuncionarios.getSelectedRow();
                if (linha != -1) {
                    txtId.setText(modeloTabela.getValueAt(linha, 0).toString());
                    txtNome.setText(modeloTabela.getValueAt(linha, 1).toString());
                    txtCpf.setText(modeloTabela.getValueAt(linha, 2).toString());
                    cbCargo.setSelectedItem(modeloTabela.getValueAt(linha, 3).toString());
                }
            }
        });
    }

    private void limparCampos() {
        txtId.setText(""); txtNome.setText(""); txtCpf.setText("");
        cbCargo.setSelectedIndex(0); tabelaFuncionarios.clearSelection();
    }

    private void adicionarCampo(JPanel painel, String labelText, JTextField textField, GridBagConstraints gbc, int linha) {
        JLabel label = new JLabel(labelText); label.setFont(FONTE_PADRAO); label.setForeground(COR_TEXTO_ESCURO);
        textField.setFont(FONTE_PADRAO); textField.setPreferredSize(new Dimension(250, 32));
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(215, 220, 225), 1), BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        gbc.gridx = 0; gbc.gridy = linha; gbc.weightx = 0.2; painel.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = linha; gbc.weightx = 0.8; painel.add(textField, gbc);
    }

    private void configurarEstiloTabela() {
        tabelaFuncionarios.setFont(FONTE_PADRAO); tabelaFuncionarios.setRowHeight(35); 
        tabelaFuncionarios.setGridColor(new Color(240, 242, 245));
        tabelaFuncionarios.setSelectionBackground(LARANJA_SUAVE); tabelaFuncionarios.setSelectionForeground(COR_TEXTO_ESCURO);
        JTableHeader header = tabelaFuncionarios.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); header.setBackground(COR_TEXTO_ESCURO);
        header.setForeground(Color.WHITE); header.setPreferredSize(new Dimension(100, 38));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabelaFuncionarios.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }

    private JButton criarBotaoColorido(String texto, Color corFundo) {
        JButton b = new JButton(texto); b.setFont(new Font("Segoe UI", Font.BOLD, 14)); b.setForeground(Color.WHITE);
        b.setBackground(corFundo); b.setFocusPainted(false); b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(150, 38)); b.setCursor(new Cursor(Cursor.HAND_CURSOR)); return b;
    }

    private JButton criarBotaoBorda(String texto) {
        JButton b = new JButton(texto); b.setFont(FONTE_PADRAO); b.setForeground(COR_TEXTO_ESCURO); b.setBackground(Color.WHITE);
        b.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 211))); b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(100, 38)); b.setCursor(new Cursor(Cursor.HAND_CURSOR)); return b;
    }
}