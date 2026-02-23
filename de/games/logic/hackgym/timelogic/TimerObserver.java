package de.games.logic.hackgym.timelogic;

/**
 * Dieses Interface muss von Klassen implementiert werden, die den GameTimer beobachten.*/
public interface TimerObserver {
    void onGameTimerStart(long startTime, long totalSeconds);
    void onGameTimerFinished(boolean b);
}
