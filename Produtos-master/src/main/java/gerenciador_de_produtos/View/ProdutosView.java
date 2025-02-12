package gerenciador_de_produtos.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import gerenciador_de_produtos.Controller.ProdutosControl;

public class ProdutosView extends JFrame {
    private ProdutosControl gerenciadorGUI;
    private JTextField searchField;
    private JTable table;
    private JLabel imagePreviewLabel = new JLabel(); // Label para exibir a imagem
    private String imagemCaminho = "";
    private JTextArea outputArea;

    public JTextArea getOutputArea() {
        return outputArea;
    }

    public JTable getTable() {
        return table;
    }

    public ProdutosView() {
        gerenciadorGUI = new ProdutosControl(this);
        outputArea = new JTextArea();
        // Configuração da janela
        setTitle("Gerenciador de Estoque");
        setSize(800, 600); // Ajustando o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout da interface
        setLayout(new BorderLayout());

        // Painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Buscar por Nome:"));
        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1)); // Cor mais suave
        inputPanel.add(searchField);

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(23, 162, 184));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(new SearchButtonListener());
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1));
        add(scrollPane, BorderLayout.SOUTH);

        // Tabela para exibir os produtos
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[] { "ID", "Nome", "Preço", "Quantidade", "Categoria", "Descrição", "Imagem" }, 0));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setSelectionBackground(new Color(52, 58, 64)); // Cor de fundo quando selecionado
        table.setSelectionForeground(Color.WHITE); // Cor do texto selecionado

        // Customizando cabeçalho da tabela
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(23, 162, 184));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Ajustando o tamanho das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Preço
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Quantidade
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Categoria
        table.getColumnModel().getColumn(5).setPreferredWidth(200); // Descrição
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Imagem

        // Método para renderizar a imagem na tabela
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value != null) {
                    String imagePath = (String) value;
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    int width = 80; // Tamanho ajustável para exibição na tabela
                    int height = 80;
                    imageIcon = new ImageIcon(
                            imageIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
                    setIcon(imageIcon);
                } else {
                    setIcon(null);
                }
            }
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                if (col == 6) {
                    String imagePath = (String) table.getValueAt(row, col);
                    if (imagePath != null && !imagePath.isEmpty()) {
                        JDialog imageDialog = new JDialog();
                        imageDialog.setTitle("Visualizar Imagem");
                        imageDialog.setSize(600, 600);
                        imageDialog.setLocationRelativeTo(null);
                        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
                        imageDialog.add(imageLabel);
                        imageDialog.setVisible(true);
                    }
                }
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1));
        add(tableScrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton addButton = new JButton("Adicionar");
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addButton.setFocusPainted(false);
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remover");
        removeButton.setBackground(new Color(220, 53, 69));
        removeButton.setForeground(Color.WHITE);
        removeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(new RemoveButtonListener());
        buttonPanel.add(removeButton);

        JButton editButton = new JButton("Editar");
        editButton.setBackground(new Color(255, 193, 7));
        editButton.setForeground(Color.WHITE);
        editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        editButton.setFocusPainted(false);
        editButton.addActionListener(new EditButtonListener());
        buttonPanel.add(editButton);

        JButton exitButton = new JButton("Sair");
        exitButton.setBackground(new Color(108, 117, 125));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> {
            dispose();

        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        gerenciadorGUI.listarProdutos();
    }

    // Listener do botão Adicionar Produto
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gerenciadorGUI.abrirDialogoAdicionarProduto();
        }
    }

    // Listener do botão Remover Produto
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gerenciadorGUI.removerProduto();
        }
    }

    // Listener do botão Editar Produto
    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gerenciadorGUI.abrirDialogoEditarProduto();
        }
    }

    // Listener do botão Buscar
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gerenciadorGUI.buscarProdutoPorNome(searchField.getText().trim());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutosView gui = new ProdutosView();
            gui.setVisible(true);
        });
    }
}