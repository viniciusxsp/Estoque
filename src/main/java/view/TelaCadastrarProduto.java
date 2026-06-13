package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaProduto extends JFrame {
    private JTextField txtId, txtNome, txtQuantidade, txtPreco;
    private JButton btnSalvar, btnExcluir, btnLimpar;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela; // Gerenciador dos dados da tabela

    // Paleta de cores: Laranja e Branco
    private final Color COR_FUNDO = new Color(248, 249, 250);       
    private final Color COR_CARD = new Color(255, 255, 255);        
    private final Color COR_TEXTO_ESCURO = new Color(43, 58, 66);   
    private final Color LARANJA_PRINCIPAL = new Color(230, 126, 34); 
    private final Color LARANJA_ESCURO = new Color(211, 84, 0);      
    private final Color LARANJA_SUAVE = new Color(253, 242, 233);   
    private final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);

    public TelaProduto() {
        setTitle("Gerenciamento de Estoque - Produtos");
        setSize(850, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(20, 20));

        // --- 1. TOPO: CABEÇALHO ---
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setBackground(LARANJA_ESCURO);
        painelHeader.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        JLabel lblTituloTela = new JLabel("Controle de Estoque > Produtos");
        lblTituloTela.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTituloTela.setForeground(Color.WHITE);
        painelHeader.add(lblTituloTela, BorderLayout.WEST);
        add(painelHeader, BorderLayout.NORTH);

        // --- 2. CENTRO: FORMULÁRIO + TABELA ---
        JPanel painelCentral = new JPanel(new BorderLayout(0, 20));
        painelCentral.setBackground(COR_FUNDO);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Formulário
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
        adicionarCampo(painelForm, "Nome do Produto:", txtNome = new JTextField(), gbc, 1);
        adicionarCampo(painelForm, "Quantidade:", txtQuantidade = new JTextField(), gbc, 2);
        adicionarCampo(painelForm, "Preço Unitário (R$):", txtPreco = new JTextField(), gbc, 3);

        painelCentral.add(painelForm, BorderLayout.NORTH);

        // Configuração da Tabela dinâmica
        String[] colunas = {"Código", "Nome do Produto", "Qtd. Estoque", "Preço"};
        modeloTabela = new DefaultTableModel(colunas, 0); // Começa com 0 linhas
        tabelaProdutos = new JTable(modeloTabela);
        configurarEstiloTabela();
        
        // Adicionando alguns dados iniciais de teste
        modeloTabela.addRow(new Object[]{"001", "Arroz Integral 5kg", "120", "24.90"});
        modeloTabela.addRow(new Object[]{"002", "Feijão Carioca 1kg", "85", "7.80"});

        JScrollPane scrollTabela = new JScrollPane(tabelaProdutos);
        scrollTabela.getViewport().setBackground(COR_CARD);
        scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(230, 235, 240)));
        
        painelCentral.add(scrollTabela, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);

        // --- 3. INFERIOR: BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        btnLimpar = criarBotaoBorda("Limpar");
        btnExcluir = criarBotaoColorido("Excluir", new Color(217, 83, 79)); 
        btnSalvar = criarBotaoColorido("Salvar Produto", LARANJA_PRINCIPAL); 

        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSalvar);
        add(painelBotoes, BorderLayout.SOUTH);

        // ==========================================
        // FUNCIONALIDADES DOS BOTÕES (AÇÕES)
        // ==========================================

        // 1. AÇÃO DO BOTÃO SALVAR
        btnSalvar.addActionListener(e -> {
            // Validação simples: não deixa salvar se os campos principais estiverem vazios
            if (txtId.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha o Código e o Nome do produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Pega os dados dos campos de texto
            String id = txtId.getText();
            String nome = txtNome.getText();
            String qtd = txtQuantidade.getText();
            String preco = txtPreco.getText();

            // Adiciona a nova linha no modelo da tabela
            modeloTabela.addRow(new Object[]{id, nome, qtd, preco});

            // Feedback visual de sucesso e limpa os campos
            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        });

        // 2. AÇÃO DO BOTÃO LIMPAR
        btnLimpar.addActionListener(e -> {
            limparCampos();
        });

        // 3. AÇÃO DO BOTÃO EXCLUIR
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();
            
            // Verifica se o usuário de fato selecionou uma linha para apagar
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o produto selecionado?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                modeloTabela.removeRow(linhaSelecionada);
                limparCampos();
            }
        });

        // 4. EVENTO DE CLIQUE NA TABELA (Preenche os campos ao clicar em uma linha)
        tabelaProdutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linhaSelecionada = tabelaProdutos.getSelectedRow();
                if (linhaSelecionada != -1) {
                    txtId.setText(modeloTabela.getValueAt(linhaSelecionada, 0).toString());
                    txtNome.setText(modeloTabela.getValueAt(linhaSelecionada, 1).toString());
                    txtQuantidade.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());
                    txtPreco.setText(modeloTabela.getValueAt(linhaSelecionada, 3).toString());
                }
            }
        });
    }

    // Método auxiliar para limpar o texto de todos os campos do formulário
    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtQuantidade.setText("");
        txtPreco.setText("");
        tabelaProdutos.clearSelection(); // Remove a seleção da tabela
    }

    private void adicionarCampo(JPanel painel, String labelText, JTextField textField, GridBagConstraints gbc, int linha) {
        JLabel label = new JLabel(labelText);
        label.setFont(FONTE_PADRAO);
        label.setForeground(COR_TEXTO_ESCURO);
        
        textField.setFont(FONTE_PADRAO);
        textField.setPreferredSize(new Dimension(250, 32));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(215, 220, 225), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        gbc.gridx = 0; gbc.gridy = linha; gbc.weightx = 0.2;
        painel.add(label, gbc);
        
        gbc.gridx = 1; gbc.gridy = linha; gbc.weightx = 0.8;
        painel.add(textField, gbc);
    }

    private void configurarEstiloTabela() {
        tabelaProdutos.setFont(FONTE_PADRAO);
        tabelaProdutos.setRowHeight(35); 
        tabelaProdutos.setGridColor(new Color(240, 242, 245));
        tabelaProdutos.setSelectionBackground(LARANJA_SUAVE);
        tabelaProdutos.setSelectionForeground(COR_TEXTO_ESCURO);

        JTableHeader header = tabelaProdutos.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(COR_TEXTO_ESCURO);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 38));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabelaProdutos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabelaProdutos.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    }

    private JButton criarBotaoColorido(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(corFundo);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setPreferredSize(new Dimension(150, 38));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private JButton criarBotaoBorda(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(FONTE_PADRAO);
        botao.setForeground(COR_TEXTO_ESCURO);
        botao.setBackground(Color.WHITE);
        botao.setBorder(BorderFactory.createLineBorder(new Color(200, 205, 211)));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(100, 38));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }
}