package de.games.gui.hackgym.sections;

import de.games.gui.hackgym.GuiController;
import javax.swing.*;
import java.awt.*;

/**
 * Endbildschirm zur Anzeige des Spielergebnisses.
 * Das ScoreBoard zeigt den erreichten Score, den Highscore sowie
 * einen Dialogtext und ermöglicht die Rückkehr zum Hauptmenü.
 */
public class ScoreBoard extends JPanel {
    private final JLabel scoreLabel;
    private final DialogSection dialogSection;
    private int highscore;
    private int score;

    /**
     * Erstellt das ScoreBoard und initialisiert alle UI-Komponenten.
     */
    public ScoreBoard() {


        //Recruiter-Bild
        ImageIcon icon = ImageCache.getRecruiterIcon();
        JLabel recruiterImage = new JLabel(icon);
        recruiterImage.setHorizontalAlignment(SwingConstants.CENTER);
        recruiterImage.setVerticalAlignment(SwingConstants.CENTER);

        //UI Elemente
        JButton ende = new JButton("Zurück zum Hauptmenü");
        ende.addActionListener(e ->
                GuiController.getGuiController().returnToMenu()
        );
        ende.setBackground(new Color(200, 220,255));

        dialogSection = new DialogSection();

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 48));
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(255, 200, 120));

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Dialog (links)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 0.3;
        add(dialogSection, gbc);

        // RecruiterImage (rechts)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
        add(recruiterImage, gbc);

        //ScoreLabel über volle Breite
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        add(scoreLabel, gbc);

        //Button
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        add(ende, gbc);
    }

    /**
     * Setzt den Highscore und aktualisiert die Anzeige.
     * @param newHighscore der neue Highscore
     */
    public void setHighScore(int newHighscore) {
        this.highscore = newHighscore;
        scoreLabel.setText("<html>Score: " + score + "<br>" +"<br>Highscore: " + highscore + "</html>");
    }

    /**
     * Setzt den aktuellen Score und aktualisiert die Anzeige.
     * @param score der erreichte Punktestand
     */
    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText("<html>Score: " + score + "<br>" +"<br>Highscore: " + highscore + "</html>");
    }

    /**
     * Liefert den Dialogbereich des ScoreBoards.
     * @return die DialogSection des ScoreBoards
     */
    public DialogSection getDialogSection() {
        return dialogSection;
    }
}
