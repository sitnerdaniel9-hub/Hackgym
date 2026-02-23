import de.games.gui.MainWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                MainWindow.getMainWindow().setVisible(true);
        });
    }
}