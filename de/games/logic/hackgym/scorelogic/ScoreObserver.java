package de.games.logic.hackgym.scorelogic;

/**
 *Dieses Interface muss von allen Klassen implementiert werden, die den Score beobachten.
 */
public interface ScoreObserver {
    /**
     * Wird aufgerufen, wenn sich der aktuelle Punktestand geändert hat.
     * @param newScore der neue aktuelle Punktestand
     */
    void updateScore(int newScore);

    /**
     * Wird aufgerufen, wenn ein neuer Highscore erreicht wurde.
     * @param newHighscore der neue Highscore
     * @param points die Punktzahl, die zum neuen Highscore geführt hat
     */
    void updateHighscore(int newHighscore, int points);
}
