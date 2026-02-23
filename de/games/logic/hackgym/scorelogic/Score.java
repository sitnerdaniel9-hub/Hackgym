package de.games.logic.hackgym.scorelogic;

import de.games.logic.hackgym.enums.Difficulty;

/**
 * Verwaltet den aktuellen Punktestand sowie die Highscores je Schwierigkeitsgrad.
 * Die Klasse ist als Singleton umgesetzt und informiert registrierte
 * {@link ScoreObserver} über Änderungen.
 */
public class Score {
    private static final int MIN = 0;
    private static Score score;
    private int points;
    private int highscoreEasy, highScoreMedium, highScoreHard;
    private ScoreObserver scoreObserver;
    private boolean roundEnd;
    private static Difficulty curDifficulty;

    /**
     * Erstellt ein neues Score-Objekt und registriert einen ScoreObserver
     * @param difficulty der aktuelle Schwierigkeitsgrad
     * @param observer der zu registrierende ScoreObserver
     */
    private Score(Difficulty difficulty, ScoreObserver observer) {
        addObserver(observer);
        curDifficulty = difficulty;
        points = MIN;
        highscoreEasy = MIN;
        highScoreMedium = MIN;
        highScoreHard = MIN;
        roundEnd = false;

    }

    /**
     * Liefert die Singleton-Instanz von {@code Score}.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     * @param difficulty der aktuelle Schwierigkeitsgrad
     * @param observer der ScoreObserver zur Registrierung
     * @return die Singleton-Instanz von {@code Score}
     */
    public static Score getScore(Difficulty difficulty, ScoreObserver observer) {
        if (score == null) {
            score = new Score(difficulty, observer);
        }
        curDifficulty = difficulty;

        return score;
    }

    /**
     * Verändert den aktuellen Punktestand um den angegebenen Wert.
     * Der Punktestand kann dabei nicht unter 0 fallen.
     * @param points die hinzuzufügenden (oder abzuziehenden) Punkte
     */
    public void setPoints(int points) {
        if (this.points + points < MIN) {
            this.points = MIN;
        } else {
            this.points += points;
        }
        notifyObserver();
    }

    /**
     * Liefert den aktuellen Punktestand.
     * @return der aktuelle Punktestand
     */
    public int getPoints() {
        return points;
    }

    /**
     * Liefert den Highscore für den aktuell gesetzten Schwierigkeitsgrad.
     * @return der Highscore des aktuellen Schwierigkeitsgrads
     */
    public int getHighscore() {
        switch (curDifficulty) {
            case EASY -> {
                return highscoreEasy;
            }
            case MEDIUM -> {
                return highScoreMedium;
            }
            case HARD -> {
                return highScoreHard;
            }

        }
        return 0;
    }

    /**
     * Setzt den Highscore für den aktuell gesetzten Schwierigkeitsgrad
     * auf den angegebenen Wert.
     * @param highscore der neue Highscore
     */
    public void setHighscore(int highscore) {
        switch (curDifficulty) {
            case EASY -> {
                highscoreEasy = highscore;
            }
            case MEDIUM -> {
                highScoreMedium = highscore;
            }
            case HARD -> {
                highScoreHard = highscore;
            }

        }
        notifyObserver();
    }

    /**
     * Registriert einen {@link ScoreObserver}, der über Änderungen
     * des Punktestands und des Highscores informiert wird.
     * @param observer der zu registrierende Observer
     */
    public void addObserver(ScoreObserver observer) {
        scoreObserver = observer;
    }

    /**
     * Benachrichtigt den registrierten {@link ScoreObserver} und veranlasst ihn zu Updates
     */
    public void notifyObserver() {
        scoreObserver.updateScore(points);
        if (roundEnd) {
            scoreObserver.updateHighscore(getHighscore(), points);
        }

    }

    /**
     * Markiert das Ende einer Runde.
     * Falls der aktuelle Punktestand höher als der bisherige Highscore ist,
     * wird der Highscore aktualisiert.
     */
    public void setRoundEnd() {
        roundEnd = true;
        int highscore = getHighscore();
        if (points > highscore) {
            setHighscore(points);
        }
        notifyObserver();
    }

    /**
     * Setzt den Score für eine neue Runde zurück.
     * Der Punktestand wird auf den Minimalwert gesetzt und der
     * Rundenzustand zurückgesetzt.
     */
    public void resetForNewRound() {
        roundEnd = false;
        points = MIN;
        notifyObserver();
    }
}
