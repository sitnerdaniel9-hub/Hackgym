package de.games.gui.hackgym.sections;

import de.games.gui.hackgym.GuiController;
import javax.swing.*;
import java.awt.*;

/**
 * Unterer Steuerungsbereich des Spielbildschirms.
 * Die BottomSection stellt Bedienelemente zum Überspringen
 * von Aufgaben, zum Beenden des Spiels sowie ein Notizenfeld bereit.
 * Die Klasse ist als Singleton umgesetzt.
 */
public class BottomSection extends JPanel {

    private final GuiController guiController;
    private static BottomSection instance;

    /**
     * Liefert die Singleton-Instanz der BottomSection.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     * @return die Singleton-Instanz der BottomSection
     */
    public static BottomSection getBottomSection() {
        if (instance == null) {
            instance = new BottomSection();
        }
        return instance;
    }

    private BottomSection() {
        guiController = GuiController.getGuiController();

        setLayout(new BorderLayout(10, 10));

        // Skip + End Buttons
        JButton skipButton = new JButton("Skip");
        skipButton.setFont(new Font("Arial", Font.BOLD, 20));
        skipButton.addActionListener(e -> {
            guiController.skipTask();
        });

        JButton endButton = new JButton("Beenden");
        endButton.setFont(new Font("Arial", Font.BOLD, 20));
        endButton.addActionListener(e -> {
            guiController.returnToMenu();
        });

        add(skipButton, BorderLayout.WEST);
        add(endButton, BorderLayout.EAST);

        // Notizenfeld
        JTextArea notesArea = new JTextArea(2, 20);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesArea.setBorder(BorderFactory.createTitledBorder("Notizen"));

        add(new JScrollPane(notesArea), BorderLayout.CENTER);
    }
}
