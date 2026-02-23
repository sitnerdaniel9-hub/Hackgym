package de.games.gui.hackgym;

import de.games.gui.hackgym.sections.DialogSection;
import de.games.logic.hackgym.scorelogic.ScoreObserver;
import de.games.logic.hackgym.taskManagement.Task;
import de.games.logic.hackgym.taskManagement.TaskObserver;
import de.games.logic.hackgym.timelogic.TimerObserver;
import javax.swing.*;

/**
 * Diese Klasse implementiert alle Beobachter und ist damit für die Reaktion auf Änderungen in der Logik verantwortlich.
 * Sie reicht die passende Reaktion auf ein Event an den {@link GuiController} weiter.
 */
public class GameListener implements TimerObserver, ScoreObserver, TaskObserver {
    private final GuiController guiController;
    private static GameListener gameListener;

    private GameListener() {
        guiController = GuiController.getGuiController();
    }

    /**
     * Liefert die Singleton-Instanz des GameListener.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     * @return die Singleton-Instanz des GameListener
     */
    public static GameListener getGameListener() {
        if (gameListener == null) {
            gameListener = new GameListener();
        }

        return gameListener;
    }

    /**
     * Wird aufgerufen, wenn sich der aktuelle Punktestand ändert.
     * Aktualisiert die Anzeige des Punktestands in der GUI.
     * @param newScore der neue aktuelle Punktestand
     */
    @Override
    public void updateScore(int newScore) {
        guiController.getScoreSection().setScore(newScore);

    }

    /**
     * Wird aufgerufen, wenn ein neuer Highscore ermittelt wurde.
     * Aktualisiert das Scoreboard und setzt den passenden Dialogtext
     * abhängig vom Spielergebnis.
     * @param newHighscore der neue Highscore
     * @param points die erreichte Punktzahl der Runde
     */
    @Override
    public void updateHighscore(int newHighscore, int points) {
        guiController.getScoreBoard().setHighScore(newHighscore);
        guiController.getScoreBoard().setScore(points);
        DialogSection dialogSection = guiController.getScoreBoard().getDialogSection();
        if (points == newHighscore) {
            dialogSection.setText(dialogSection.getScoreBoardSuccessDialog());
        } else {
            dialogSection.setText(dialogSection.getScoreBoardFailDialog());
        }
    }

    /**
     * Wird aufgerufen, wenn eine neue Aufgabe aktiv wird.
     * Veranlasst die Anzeige der neuen Aufgabe in der GUI.
     * @param newTask die neue aktive Aufgabe
     */
    @Override
    public void onTaskChanged(Task newTask) {
        guiController.showTaskSection(newTask);
    }

    /**
     * Wird aufgerufen, wenn der Spieltimer startet.
     * Initialisiert und startet den Timer in der grafischen Oberfläche.
     * @param startTime der Startzeitpunkt des Timers
     * @param totalSeconds die Gesamtlaufzeit des Timers in Sekunden
     */
    @Override
    public void onGameTimerStart(long startTime, long totalSeconds) {
        SwingUtilities.invokeLater(() -> {
            guiController.getTimerSection().setStartTime(startTime);
            guiController.getTimerSection().setTotalSeconds(totalSeconds);
            guiController.getTimerSection().startTimer();
        });
    }

    /**
     * Wird aufgerufen, wenn der Spieltimer endet.
     * Zeigt abhängig vom Ergebnis entweder den Endbildschirm
     * oder kehrt zum Hauptmenü zurück.
     * @param b {@code true}, wenn das Spiel regulär beendet wurde,
     *          {@code false}, wenn es abgebrochen wurde
     */
    @Override
    public void onGameTimerFinished(boolean b) {
        if (b) {
            guiController.showScoreSection();
        } else {
            guiController.returnToMenu();
        }
    }
}
