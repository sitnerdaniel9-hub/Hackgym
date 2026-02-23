package de.games.gui.hackgym;

import de.games.gui.MainWindow;
import de.games.gui.hackgym.sections.*;
import de.games.logic.hackgym.GameController;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import de.games.logic.hackgym.taskManagement.Task;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Diese Klasse steuert die GUI und damit alle Screens.
 * Sie kommuniziert mit dem {@link GameController} und allen UI Elementen.
 * Diese Klasse ist als Singleton umgesetzt.
 */
public class GuiController {
    private ScoreBoard scoreBoard;
    private GameScreen gameScreen;
    private MusicController musicController;
    private GameController gameController;
    private static GuiController guiController;
    private CardLayout cardLayout;
    private JPanel cardPanel;


    private GuiController() {}

    /**
     * Liefert die Singleton-Instanz des GuiController.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     * @return die Singleton-Instanz des GuiController
     */
    public static GuiController getGuiController() {
        if (guiController == null) {
            guiController = new GuiController();
        }

        return guiController;
    }

    /**
     * Zeigt den Aufgabenbereich für die übergebene Aufgabe an.
     * Aktualisiert den Dialogtext abhängig von Aufgabentyp und
     * Schwierigkeitsgrad und baut die passende {@link TaskSection}.
     * @param task die anzuzeigende Aufgabe
     */
    public void showTaskSection(Task task) {
        DialogSection dialog = gameScreen.getDialogSection();
        HashMap<TaskType, String> map;
        if (task.getDifficulty() == Difficulty.EASY) {
            map = dialog.getEasyTaskDialogs();
        } else if (task.getDifficulty() == Difficulty.MEDIUM) {
            map = dialog.getMediumTaskDialogs();
        } else {
            map = dialog.getHardTaskDialogs();
        }
        dialog.setText(map.get(task.getType()));
        JPanel taskContainer = gameScreen.getTaskContainer();
        taskContainer.removeAll();
        TaskSection section = TaskSection.buildTaskSection(task);
        if (section != null) {
            section.buildTaskSection();
            taskContainer.add(section, BorderLayout.CENTER);
            taskContainer.revalidate();
            taskContainer.repaint();
        }
    }

    /**
     * Initialisiert und startet das Spiel.
     * Erstellt alle notwendigen Screens, richtet das {@link CardLayout}
     * ein und zeigt den Schwierigkeitsauswahl-Bildschirm an.
     */
    public void startGame() {
        gameController = GameController.getGameController();
        MainWindow mainWindow = MainWindow.getMainWindow();

        // Vorherige Inhalte entfernen, falls vorhanden
        mainWindow.getContentPane().removeAll();
        // CardLayout + Panel initialisieren
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        // Panels erstellen
        DifficultyScreen difficultyScreen = DifficultyScreen.getDifficultyScreen();
        scoreBoard = new ScoreBoard();
        gameScreen = new GameScreen();
        musicController = new MusicController();
        musicController.loadTrack("Easy", "/de/games/gui/hackgym/ressource/sound/easyTrack.wav");
        musicController.loadTrack("Medium", "/de/games/gui/hackgym/ressource/sound/mediumTrack.wav");
        musicController.loadTrack("Hard", "/de/games/gui/hackgym/ressource/sound/hardTrack.wav");

        // Panels hinzufügen
        cardPanel.add(difficultyScreen, "Schwierigkeit");
        cardPanel.add(scoreBoard, "Endbildschirm");
        cardPanel.add(gameScreen, "Spielbildschirm");

        // Panel ins Fenster setzen
        mainWindow.add(cardPanel);

        // Layout aktualisieren
        mainWindow.revalidate();
        mainWindow.repaint();

        // Startscreen anzeigen
        showDifficultyScreen();
    }

    private void showDifficultyScreen() {
        cardLayout.show(cardPanel, "Schwierigkeit");
    }

    /**
     * Setzt den Schwierigkeitsgrad des Spiels.
     * Startet die passende Hintergrundmusik und wechselt
     * zum Spielbildschirm.
     * @param difficulty der gewählte Schwierigkeitsgrad
     */
    public void setDifficulty(Difficulty difficulty) {
        gameController.changeDifficulty(difficulty);
        switch (difficulty) {
            case EASY -> musicController.playLoop("Easy");
            case MEDIUM -> musicController.playLoop("Medium");
            case HARD -> musicController.playLoop("Hard");
        }
        showGameScreen();
    }

    private void showGameScreen() {
        cardLayout.show(cardPanel, "Spielbildschirm");
    }

    /**
     * Übergibt die vom Benutzer eingegebene Lösung an den {@link GameController}.
     * @param solution die eingegebene Lösung als String
     */
    public void submitSolution(String solution) {
        gameController.submitTask(solution);
    }

    /**
     * Liefert die {@link ScoreSection} der Benutzeroberfläche.
     * @return die ScoreSection
     */
    public ScoreSection getScoreSection() {
        return ScoreSection.getScoreSection();
    }

    /**
     * Liefert die {@link TimerSection} der Benutzeroberfläche.
     * @return die TimerSection
     */
    public TimerSection getTimerSection() {
        return TimerSection.getTimerSection();
    }

    /**
     * Überspringt die aktuelle Aufgabe.
     */
    public void skipTask() {
        gameController.skipTask();
    }

    /**
     * Kehrt zum Hauptmenü zurück.
     * Stoppt die Musik, zeigt die Schwierigkeitsauswahl
     */
    public void returnToMenu() {
        musicController.stopAll();
        showDifficultyScreen();
        gameController.returnToMainMenu();
    }


    /**
     * Beendet das Spiel vollständig.
     * Setzt relevante Referenzen zurück und zeigt wieder
     * die Spielauswahl im {@link MainWindow} an.
     */
    public void endGame() {
        scoreBoard = null;
        MainWindow.getMainWindow().showGameCollection();
        gameController.endGame();
    }

    /**
     * Zeigt den Endbildschirm mit dem Scoreboard an.
     */
    public void showScoreSection() {
        cardLayout.show(cardPanel, "Endbildschirm");
    }

    /**
     * Liefert das aktuelle {@link ScoreBoard}.
     * @return das ScoreBoard
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
