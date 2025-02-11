package org.Produtos.Control;
import org.Produtos.Model.DTO;
import org.Produtos.Model.DAO;
import org.Produtos.View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.*;
import java.awt.*;


public class Control {
    // Método para listar os produtos automaticamente
    private void listarProdutos() {
        List<DTO> produtos = DAO.listAllProdutos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa as linhas atuais da tabela

        // Verifica se há produtos
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            // Adiciona cada produto na tabela, incluindo imagem e outros detalhes
            for (DTO del.addRow(new Object[] {
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade(),
                        produto.getCategoria(),
                        produto.getDescricao(),
                        produto.getImagem() // Exibe o caminho da imagem na tabela
                });
            }
        }
    }

    // Listener do botão Adicionar Produto
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Cria um novo JDialog para adicionar produto
            JDialog addDialog = new JDialog(View.this, "Adicionar Produto", true);
            addDialog.setSize(400, 300);
            addDialog.setLayout(new GridLayout(9, 2, 10, 10));

            // Campos de entrada
            nomeField = new JTextField();
            precoField = new JTextField();
            quantidadeField = new JTextField();
            JTextField descricaoField = new JTextField();
            JTextField categoriaField = new JTextField();
            JTextField imagemUrlField = new JTextField(); // Campo para o link da imagem
            JButton uploadButton = new JButton("Upload Imagem");

            addDialog.add(new JLabel("Nome do Produto:"));
            addDialog.add(nomeField);
            addDialog.add(new JLabel("Preço do Produto:"));
            addDialog.add(precoField);
            addDialog.add(new JLabel("Quantidade do Produto:"));
            addDialog.add(quantidadeField);
            addDialog.add(new JLabel("Descrição do Produto:"));
            addDialog.add(descricaoField);
            addDialog.add(new JLabel("Categoria do Produto:"));
            addDialog.add(categoriaField);
            addDialog.add(new JLabel("URL da Imagem (ou botão de upload):"));
            addDialog.add(imagemUrlField); // Campo de link da imagem
            addDialog.add(uploadButton);

            // Criação do JLabel para visualização da imagem
            addDialog.add(new JLabel("Imagem do Produto:"));
            addDialog.add(imagePreviewLabel); // Adiciona o JLabel para exibir a imagem

            // Ação do botão de upload de imagem
            uploadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int resultado = fileChooser.showOpenDialog(addDialog);
                    if (resultado == JFileChooser.APPROVE_OPTION) {
                        // Salva o caminho do arquivo na variável de instância
                        imagemCaminho = fileChooser.getSelectedFile().getAbsolutePath();
                        JOptionPane.showMessageDialog(addDialog, "Imagem selecionada: " + imagemCaminho);

                        // Exibe a imagem no JLabel
                        ImageIcon imageIcon = new ImageIcon(imagemCaminho);
                        imagePreviewLabel.setIcon(imageIcon); // Define a imagem no JLabel
                    }
                }
            });

            // Botão Salvar
            JButton salvarButton = new JButton("Salvar");
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Coleta os dados dos campos
                        String nome = nomeField.getText();
                        Double preco = Double.parseDouble(precoField.getText());
                        int quantidade = Integer.parseInt(quantidadeField.getText());
                        String descricao = descricaoField.getText();
                        String categoria = categoriaField.getText();
                        String imagem = imagemUrlField.getText().trim().isEmpty() ? imagemCaminho : imagemUrlField.getText().trim(); // Usa o link se o campo não estiver vazio

                        // Cria um novo produto e adiciona
                        Produto produto = new Produto(nome, preco, quantidade, descricao, categoria, imagem);
                        gerenciador.addProduto(produto);
                        outputArea.setText("Produto adicionado: " + produto);

                        listarProdutos(); // Atualiza a tabela de produtos
                        addDialog.dispose(); // Fecha o diálogo após salvar o produto
                    } catch (NumberFormatException ex) {
                        outputArea.setText("Erro: Preço e quantidade devem ser números válidos.");
                    }
                }
            });

            addDialog.add(salvarButton);
            addDialog.setLocationRelativeTo(null);
            addDialog.setVisible(true); // Exibe o diálogo
        }
    }


    // Listener do botão Remover Produto
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(
                    IndexGUI.this,
                    "Digite o ID ou Nome do Produto a ser removido:",
                    "Remover Produto",
                    JOptionPane.QUESTION_MESSAGE);

            if (input != null && !input.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(input.trim());
                    gerenciador.deleteProduto(id);
                    outputArea.setText("Produto removido com ID: " + id);

                    // Atualiza a lista de produtos após a remoção
                    listarProdutos(); // Método para listar os produtos na interface

                } catch (NumberFormatException ex) {
                    List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                    if (!produtos.isEmpty()) {
                        Produto produto = produtos.get(0);
                        gerenciador.deleteProduto(produto.getId());
                        outputArea.setText("Produto removido: " + produto.getNome());

                        // Atualiza a lista de produtos após a remoção
                        listarProdutos(); // Atualiza a tabela de produtos após remoção
                    } else {
                        outputArea.setText("Produto não encontrado pelo nome.");
                    }
                }
            } else {
                outputArea.setText("Entrada inválida. Por favor, insira um ID ou nome válido.");
            }
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(
                    IndexGUI.this,
                    "Digite o Nome ou ID do Produto a ser editado:",
                    "Editar Produto",
                    JOptionPane.QUESTION_MESSAGE);

            if (input != null && !input.trim().isEmpty()) {
                try {
                    // Tenta buscar o produto pelo ID
                    int id = Integer.parseInt(input.trim());
                    Produto produto = gerenciador.getProduto(id);

                    if (produto != null) {
                        // Cria o JDialog de edição do produto
                        JDialog editDialog = new JDialog(IndexGUI.this, "Editar Produto", true);
                        editDialog.setSize(400, 400);
                        editDialog.setLayout(new GridLayout(9, 2, 10, 10));

                        // Campos preenchidos com os dados do produto
                        JTextField nomeField = new JTextField(produto.getNome());
                        JTextField precoField = new JTextField(String.valueOf(produto.getPreco()));
                        JTextField quantidadeField = new JTextField(String.valueOf(produto.getQuantidade()));
                        JTextField descricaoField = new JTextField(produto.getDescricao());
                        JTextField categoriaField = new JTextField(produto.getCategoria());
                        JLabel imagePreviewLabel = new JLabel(); // Para exibir a imagem do produto
                        final String[] imagemCaminho = { produto.getImagem() };

                        // Exibe a imagem do produto, se houver
                        if (imagemCaminho[0] != null && !imagemCaminho[0].isEmpty()) {
                            ImageIcon imageIcon = new ImageIcon(imagemCaminho[0]);
                            imagePreviewLabel.setIcon(imageIcon);
                        }

                        // Campos do formulário de edição
                        editDialog.add(new JLabel("Nome do Produto:"));
                        editDialog.add(nomeField);
                        editDialog.add(new JLabel("Preço do Produto:"));
                        editDialog.add(precoField);
                        editDialog.add(new JLabel("Quantidade do Produto:"));
                        editDialog.add(quantidadeField);
                        editDialog.add(new JLabel("Descrição do Produto:"));
                        editDialog.add(descricaoField);
                        editDialog.add(new JLabel("Categoria do Produto:"));
                        editDialog.add(categoriaField);
                        editDialog.add(new JLabel("Imagem do Produto:"));
                        editDialog.add(imagePreviewLabel);

                        // Botão de upload da imagem
                        JButton uploadButton = new JButton("Alterar Imagem");
                        uploadButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                int resultado = fileChooser.showOpenDialog(editDialog);
                                if (resultado == JFileChooser.APPROVE_OPTION) {
                                    imagemCaminho[0] = fileChooser.getSelectedFile().getAbsolutePath(); // Atualiza a imagem
                                    JOptionPane.showMessageDialog(editDialog,
                                            "Imagem selecionada: " + imagemCaminho[0]);

                                    // Exibe a imagem selecionada no JLabel
                                    ImageIcon imageIcon = new ImageIcon(imagemCaminho[0]);
                                    imagePreviewLabel.setIcon(imageIcon);
                                }
                            }
                        });
                        editDialog.add(uploadButton);

                        // Botão de Salvar alterações
                        JButton salvarButton = new JButton("Salvar Alterações");
                        salvarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    // Coleta os dados dos campos editados
                                    String nome = nomeField.getText();
                                    Double preco = Double.parseDouble(precoField.getText());
                                    int quantidade = Integer.parseInt(quantidadeField.getText());
                                    String descricao = descricaoField.getText();
                                    String categoria = categoriaField.getText();

                                    // Atualiza o produto com os novos dados
                                    produto.setNome(nome);
                                    produto.setPreco(preco);
                                    produto.setQuantidade(quantidade);
                                    produto.setDescricao(descricao);
                                    produto.setCategoria(categoria);
                                    produto.setImagem(imagemCaminho[0]);

                                    // Atualiza o produto no gerenciador de estoque
                                    gerenciador.updateProduto(produto.getId(), produto);

                                    // Atualiza a tabela de produtos
                                    listarProdutos();
                                    JOptionPane.showMessageDialog(editDialog, "Produto atualizado com sucesso!");
                                    editDialog.dispose(); // Fecha o diálogo
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(editDialog,
                                            "Erro: Preço e quantidade devem ser números válidos.");
                                }
                            }
                        });
                        editDialog.add(salvarButton);

                        // Exibe o diálogo
                        editDialog.setLocationRelativeTo(null);
                        editDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado com o ID: " + id, "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Se o ID não for numérico, tenta buscar o produto pelo nome
                    List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                    if (!produtos.isEmpty()) {
                        Produto produto = produtos.get(0);
                        // Cria o JDialog de edição para o produto encontrado
                    } else {
                        JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }


    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nomeBusca = searchField.getText().trim();
            if (!nomeBusca.isEmpty()) {
                List<Produto> produtos = gerenciador.searchProdutoByName(nomeBusca);
                if (produtos.isEmpty()) {
                    JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado.", "Busca",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Cria uma nova tela para exibir os resultados
                    IndexGUI.SearchResultDialog dialog = new IndexGUI.SearchResultDialog(produtos);
                    dialog.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(IndexGUI.this, "Por favor, insira um nome para buscar.", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class SearchResultDialog extends JDialog {
        public SearchResultDialog(List<Produto> produtos) {
            setTitle("Resultados da Busca");
            setSize(400, 300);
            setLocationRelativeTo(null);

            String[] columns = { "ID", "Nome", "Preço", "Quantidade" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable resultTable = new JTable(model);

            for (Produto produto : produtos) {
                model.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getPreco(),
                        produto.getQuantidade() });
            }

            add(new JScrollPane(resultTable), BorderLayout.CENTER);

            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(e -> dispose());
            add(closeButton, BorderLayout.SOUTH);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IndexGUI gui = new IndexGUI();
            gui.setVisible(true);
        });
    }
}
