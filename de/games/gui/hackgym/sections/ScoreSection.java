package de.games.gui.hackgym.sections;

import javax.swing.*;
import java.awt.*;

/**
 * UI-Komponente zur Anzeige des aktuellen Punktestands.
 * Die ScoreSection zeigt den Score während des Spiels an
 * und wird als Singleton verwaltet.
 */
public class ScoreSection extends JPanel {
    private final JLabel scoreLabel;
    private static ScoreSection scoreSection;

    private ScoreSection() {
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(scoreLabel);
    }

    /**
     * Liefert die Singleton-Instanz der ScoreSection.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     * @return die Singleton-Instanz der ScoreSection
     */
    public static ScoreSection getScoreSection() {
        if (scoreSection == null) {
            scoreSection = new ScoreSection();
        }
        return scoreSection;
    }

    /**
     * Aktualisiert die Anzeige des aktuellen Punktestands.
     * @param score der neue Punktestand
     */
    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
