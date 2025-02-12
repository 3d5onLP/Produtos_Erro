package gerenciador_de_produtos.Controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gerenciador_de_produtos.Model.DAO.ProdutosDAO;
import gerenciador_de_produtos.Model.DTO.ProdutosDTO;
import gerenciador_de_produtos.View.ProdutosView;

public class ProdutosControl {
    private ProdutosView indexGUI;
    private ProdutosDAO produtosDAO;

    public ProdutosControl(ProdutosView indexGUI) {
        this.indexGUI = indexGUI;
        this.produtosDAO = new ProdutosDAO(); // Inicializa o DAO
    }

    public void listarProdutos() {
        List<ProdutosDTO> produtos = produtosDAO.listAllProdutos(); // Busca produtos do banco
        DefaultTableModel model = (DefaultTableModel) indexGUI.getTable().getModel();
        model.setRowCount(0); // Limpa as linhas atuais da tabela

        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(indexGUI, "Nenhum produto cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            for (ProdutosDTO produto : produtos) {
                model.addRow(new Object[] {
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade(),
                        produto.getCategoria(),
                        produto.getDescricao(),
                        produto.getImagem()
                });
            }
        }
    }

    public void abrirDialogoAdicionarProduto() {
        JDialog addDialog = new JDialog(indexGUI, "Adicionar Produto", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(9, 2, 10, 10));

        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();
        JTextField quantidadeField = new JTextField();
        JTextField descricaoField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField imagemUrlField = new JTextField();
        JButton uploadButton = new JButton("Upload Imagem");
        JLabel imagePreviewLabel = new JLabel();

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
        addDialog.add(imagemUrlField);
        addDialog.add(uploadButton);
        addDialog.add(new JLabel("Imagem do Produto:"));
        addDialog.add(imagePreviewLabel);

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(addDialog);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String imagemCaminho = fileChooser.getSelectedFile().getAbsolutePath();
                JOptionPane.showMessageDialog(addDialog, "Imagem selecionada: " + imagemCaminho);
                imagePreviewLabel.setIcon(new ImageIcon(imagemCaminho));
            }
        });

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                Double preco = Double.parseDouble(precoField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());
                String descricao = descricaoField.getText();
                String categoria = categoriaField.getText();
                String imagem = imagemUrlField.getText().trim().isEmpty() ? null : imagemUrlField.getText().trim();

                ProdutosDTO produto = new ProdutosDTO(nome, preco, quantidade, categoria, descricao, imagem);
                produtosDAO.addProduto(produto); // Adiciona o produto no banco
                indexGUI.getOutputArea().setText("Produto adicionado: " + produto);

                listarProdutos(); // Atualiza a tabela
                addDialog.dispose();
            } catch (NumberFormatException ex) {
                indexGUI.getOutputArea().setText("Erro: Preço e quantidade devem ser números válidos.");
            }
        });

        addDialog.add(salvarButton);
        addDialog.setLocationRelativeTo(null);
        addDialog.setVisible(true);
    }

    public void removerProduto() {
        String input = JOptionPane.showInputDialog(
                indexGUI,
                "Digite o ID ou Nome do Produto a ser removido:",
                "Remover Produto",
                JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(input.trim());
                produtosDAO.deleteProduto(id); // Remove o produto do banco
                indexGUI.getOutputArea().setText("Produto removido com ID: " + id);
                listarProdutos(); // Atualiza a tabela
            } catch (NumberFormatException ex) {
                List<ProdutosDTO> produtos = produtosDAO.searchProdutoByName(input.trim());
                if (!produtos.isEmpty()) {
                    ProdutosDTO produto = produtos.get(0);
                    produtosDAO.deleteProduto(produto.getId()); // Remove o produto do banco
                    indexGUI.getOutputArea().setText("Produto removido: " + produto.getNome());
                    listarProdutos(); // Atualiza a tabela
                } else {
                    indexGUI.getOutputArea().setText("Produto não encontrado pelo nome.");
                }
            }
        } else {
            indexGUI.getOutputArea().setText("Entrada inválida. Por favor, insira um ID ou nome válido.");
        }
    }

    public void abrirDialogoEditarProduto() {
        String input = JOptionPane.showInputDialog(
                indexGUI,
                "Digite o Nome ou ID do Produto a ser editado:",
                "Editar Produto",
                JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(input.trim());
                ProdutosDTO produto = produtosDAO.getProduto(id); // Busca o produto no banco

                if (produto != null) {
                    JDialog editDialog = new JDialog(indexGUI, "Editar Produto", true);
                    editDialog.setSize(400, 400);
                    editDialog.setLayout(new GridLayout(9, 2, 10, 10));

                    JTextField nomeField = new JTextField(produto.getNome());
                    JTextField precoField = new JTextField(String.valueOf(produto.getPreco()));
                    JTextField quantidadeField = new JTextField(String.valueOf(produto.getQuantidade()));
                    JTextField descricaoField = new JTextField(produto.getDescricao());
                    JTextField categoriaField = new JTextField(produto.getCategoria());
                    JLabel imagePreviewLabel = new JLabel();
                    final String[] imagemCaminho = { produto.getImagem() };

                    if (imagemCaminho[0] != null && !imagemCaminho[0].isEmpty()) {
                        imagePreviewLabel.setIcon(new ImageIcon(imagemCaminho[0]));
                    }

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

                    JButton uploadButton = new JButton("Alterar Imagem");
                    uploadButton.addActionListener(e -> {
                        JFileChooser fileChooser = new JFileChooser();
                        int resultado = fileChooser.showOpenDialog(editDialog);
                        if (resultado == JFileChooser.APPROVE_OPTION) {
                            imagemCaminho[0] = fileChooser.getSelectedFile().getAbsolutePath();
                            imagePreviewLabel.setIcon(new ImageIcon(imagemCaminho[0]));
                        }
                    });
                    editDialog.add(uploadButton);

                    JButton salvarButton = new JButton("Salvar Alterações");
                    salvarButton.addActionListener(e -> {
                        try {
                            produto.setNome(nomeField.getText());
                            produto.setPreco(Double.parseDouble(precoField.getText()));
                            produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                            produto.setDescricao(descricaoField.getText());
                            produto.setCategoria(categoriaField.getText());
                            produto.setImagem(imagemCaminho[0]);

                            produtosDAO.updateProduto(produto.getId(), produto); // Atualiza o produto no banco
                            listarProdutos(); // Atualiza a tabela
                            JOptionPane.showMessageDialog(editDialog, "Produto atualizado com sucesso!");
                            editDialog.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(editDialog,
                                    "Erro: Preço e quantidade devem ser números válidos.");
                        }
                    });
                    editDialog.add(salvarButton);

                    editDialog.setLocationRelativeTo(null);
                    editDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(indexGUI, "Produto não encontrado com o ID: " + id, "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                List<ProdutosDTO> produtos = produtosDAO.searchProdutoByName(input.trim());
                if (!produtos.isEmpty()) {
                    ProdutosDTO produto = produtos.get(0);
                    // Abrir diálogo de edição para o produto encontrado
                } else {
                    JOptionPane.showMessageDialog(indexGUI, "Produto não encontrado.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void buscarProdutoPorNome(String nomeBusca) {
        if (!nomeBusca.isEmpty()) {
            List<ProdutosDTO> produtos = produtosDAO.searchProdutoByName(nomeBusca); // Busca produtos no banco
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(indexGUI, "Produto não encontrado.", "Busca",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe os resultados em uma nova janela
                SearchResultDialog dialog = new SearchResultDialog(produtos);
                dialog.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(indexGUI, "Por favor, insira um nome para buscar.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private class SearchResultDialog extends JDialog {
        public SearchResultDialog(List<ProdutosDTO> produtos) {
            setTitle("Resultados da Busca");
            setSize(400, 300);
            setLocationRelativeTo(null);

            String[] columns = { "ID", "Nome", "Preço", "Quantidade" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable resultTable = new JTable(model);

            for (ProdutosDTO produto : produtos) {
                model.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getPreco(),
                        produto.getQuantidade() });
            }

            add(new JScrollPane(resultTable), BorderLayout.CENTER);

            JButton closeButton = new JButton("Fechar");
            closeButton.addActionListener(e -> dispose());
            add(closeButton, BorderLayout.SOUTH);
        }
    }
}