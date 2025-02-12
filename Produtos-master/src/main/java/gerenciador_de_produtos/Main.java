package gerenciador_de_produtos;

import gerenciador_de_produtos.View.ProdutosView;

public class Main {
    public static void main(String[] args) {
        // Inicia a interface gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            ProdutosView gui = new ProdutosView(); // Cria a instância da interface gráfica
            gui.setVisible(true); // Torna a janela visível
        });
    }
}