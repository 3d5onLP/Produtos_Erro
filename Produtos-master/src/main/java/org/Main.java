package org;
import org.View.IndexGUI;

public class Main {
    public static void main(String[] args) {
        // Inicia a interface gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            IndexGUI gui = new IndexGUI(); // Cria a instância da interface gráfica
            gui.setVisible(true); // Torna a janela visível
        });
    }
}