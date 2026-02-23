package de.games.gui.hackgym.sections;

import de.games.gui.hackgym.GuiController;
import de.games.logic.hackgym.enums.Difficulty;
import javax.swing.*;
import java.awt.*;

/**
 * Bildschirm zur Auswahl des Schwierigkeitsgrads.
 */
public class DifficultyScreen extends JPanel {
    private static DifficultyScreen difficultyScreen;

    private DifficultyScreen() {
        DialogSection dialogSection = new DialogSection();
        dialogSection.setText(dialogSection.getIntroDialog());

        JButton easy = new JButton("Leicht");
        JButton medium = new JButton("Mittel");
        JButton hard = new JButton("Schwer");
        JButton gameCollection = new JButton("Zurück zur Spielesammlung");

        Dimension buttonSize = new Dimension(200, 50);  // Breite ignoriert GridBagLayout sowieso
        easy.setPreferredSize(buttonSize);
        medium.setPreferredSize(buttonSize);
        hard.setPreferredSize(buttonSize);
        gameCollection.setPreferredSize(buttonSize);

        JLabel recruiterImage = new JLabel(ImageCache.getRecruiterIcon());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        // Intro Section
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        add(dialogSection, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.NONE;
        add(recruiterImage, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Leicht
        gbc.gridy = 1;
        add(easy, gbc);

        // Mittel
        gbc.gridy = 2;
        add(medium, gbc);

        // Schwer
        gbc.gridy = 3;
        add(hard, gbc);

        // Zurück
        gbc.gridy = 4;
        add(gameCollection, gbc);

        easy.addActionListener(e -> GuiController.getGuiController().setDifficulty(Difficulty.EASY));
        medium.addActionListener(e -> GuiController.getGuiController().setDifficulty(Difficulty.MEDIUM));
        hard.addActionListener(e -> GuiController.getGuiController().setDifficulty(Difficulty.HARD));
        gameCollection.addActionListener(e -> GuiController.getGuiController().endGame());
    }

    /**
     * Liefert die Singleton-Instanz des DifficultyScreen.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt
     * und initialisiert.
     * @return die Singleton-Instanz des DifficultyScreen
     */
    public static DifficultyScreen getDifficultyScreen() {
        if (difficultyScreen == null) {
            difficultyScreen = new DifficultyScreen();
            difficultyScreen.setBackground(new Color(255, 200, 120));
        }
        return difficultyScreen;
    }
}
