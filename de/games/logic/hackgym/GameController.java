package de.games.logic.hackgym;

import de.games.gui.hackgym.GameListener;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.GameState;
import de.games.logic.hackgym.scorelogic.Score;
import de.games.logic.hackgym.taskManagement.Task;
import de.games.logic.hackgym.taskManagement.TaskManager;
import de.games.logic.hackgym.timelogic.GameTimer;

/**
 * Diese Klasse ist für die Verwaltung der Kontroller der Anwendungsschicht verantwortlich.*/
public class GameController {
    private volatile GameState state;
    private static GameController gameController;
    private TaskManager taskManager;
    private Score score;
    private GameTimer timer;

    /**privater Konstruktor verhindert Initialisierung von außen*/
    private GameController() {
        state = GameState.MENUE;
    }

    /**Initialisierung des GameControllers als Singleton*/
    public static GameController getGameController() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    /**Getter und Setter für den State
     * @return der aktuelle Gamestate*/
    public GameState getState() {
        return state;
    }

    /**Holt sich die nächste Aufgabe
     * @return die nächste Aufgabe*/
    public Task showNextTask() {
        return taskManager.selectTask();
    }

    /**Überspringt die aktuelle Aufgabe*/
    public void skipTask() {
        taskManager.skipTask();
    }

    /**beendet das Spiel*/
    public void endGame() {
        taskManager = null;
        score = null;
        gameController = null;
    }

    /**Bringt einen zurück in das Hauptmenü zurück.*/
    public void returnToMainMenu() {
        if (timer != null) {
            timer.interrupt();
            try {
                timer.join();
            } catch (InterruptedException ignored) {

            }
        }
        taskManager.clearTasks();
        score.setRoundEnd();
        state = GameState.MENUE;

    }

    /**Startet ein neues Spiel mit der geänderten Schwierigkeit
     * @param difficulty die neue Schwierigkeit*/
    public void changeDifficulty(Difficulty difficulty) {
        GameListener gameListener = GameListener.getGameListener();
        taskManager = TaskManager.getTaskManager(difficulty, gameListener);
        timer = new GameTimer();
        timer.addObserver(gameListener);
        timer.start();
        state = GameState.GAME;
        score = Score.getScore(difficulty, gameListener);
        score.resetForNewRound();
    }

    /**Reicht eine Lösung zur Bewertung ein
     * @param solution die Lösung des Spielers
     * @return gibt die nächste Aufgabe zurück. Wird nur in den Tests zur Anwendungsschicht verwendet*/
    public Task submitTask(String solution) {
        taskManager.checkSolution(solution);
        return showNextTask();

    }

    /**Erhöht den Score um die übergebenen Punkte
     * @param points die Punkte einer Aufgabe
     * */
    public void setScore(int points) {
        score.setPoints(points);
    }

    /**
     * @return gibt das Scoreobjekt zurück
     */
    public Score getScore() {
        return score;
    }

    /**Zeigt den Score und den Highscore an*/
    public void showResults() {
        score.setRoundEnd();
    }
}
