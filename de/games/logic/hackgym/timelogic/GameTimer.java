package de.games.logic.hackgym.timelogic;

import de.games.logic.hackgym.GameController;

/**
 * Diese Klasse erzeugt den Timer für das Spiel*/
public class GameTimer extends Thread {
    private TimerObserver timerObserver;
    private final GameController gameController;
    long startTime;

    /**Erzeugt ein GameTimerobjekt*/
    public GameTimer() {
        gameController = GameController.getGameController();
    }

    /**startet den Timer und beendet ihn, sobald die Zeit abgelaufen ist.*/
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        long totalSeconds = 180;
        timerObserver.onGameTimerStart(startTime, totalSeconds);
        try {
            sleep(totalSeconds*1000);
            timerObserver.onGameTimerFinished(true);
        } catch (InterruptedException e) {
            timerObserver.onGameTimerFinished(false);
        }
        gameController.showResults();

    }

    /**Fügt dem GameTimer einen TimerObserver hinzu.
     * @param observer der TimerObserver*/
    public void addObserver(TimerObserver observer) {
        timerObserver = observer;
    }
}
