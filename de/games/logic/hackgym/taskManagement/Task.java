package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;

/**
 * Diese Klasse gibt die Attribute und Methoden vor, die alle Aufgaben haben.
 */
public abstract class Task {
    private int points;
    private Difficulty difficulty;
    private TaskType type;

    /**Gibt die Schwierigkeit einer Aufgabe zurück
     * @return die Schwierigkeit der Aufgabe
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**Gibt die Punkte einer Aufgabe zurück.
     * @return die Punkte der Aufgabe*/
    public int getPoints() {
        return points;
    }

    /**Gibt den Typ der Aufgabe zurück.
     * @return der Typ der Aufgabe.
     */
    public TaskType getType() {
        return type;
    }

    /**Setzt die Schwierigkeit einer Aufgabe.
     * @param difficulty die Schwierigkeit der Aufgabe*/
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**Setzt den Typ der Aufgabe.
     * @param type der Typ der Aufgabe*/
    public void setType(TaskType type) {
        this.type = type;
    }

    /**Setzt die Punkte einer Aufgabe
     * @param points die Punkte der Aufgabe*/
    public void setPoints(int points) {
        this.points = points;
    }

    /**Prüft eine eingereichte Lösung
     * @param solution die Lösung des Spielers
     * @return Gibt zurück, ob die Lösung richtig oder falsch war.*/
    public abstract boolean checkSolution(String solution);

    /**Erstellt eine Aufgabe eines beliebigen Typs.
     * @param difficulty die Schwierigkeit der Aufgabe
     * @param type der Typ der Aufgabe
     * @return die Aufgabe*/
    public static Task createTask(Difficulty difficulty, TaskType type) {
        switch (type) {
            case SORT -> {
                return new SortTask();
            }
            case CALCULATION -> {
                return new CalculationTask(difficulty);
            }
            case CLICK_NUMBER -> {
                return new ClickNumberTask(difficulty);
            }
            case COLOR_BUTTON -> {
                return new ColorButtonTask(difficulty);
            }
            case REVERSE_WORD -> {
                return new ReverseWordTask();
            }
            case PATTERN_ERROR -> {
                return new PatternErrorTask();
            }
            case RHYTHM_BUTTON -> {
                return new RyhthmButtonTask();
            }
            case ADD_NUMBER_SEQUENCE -> {
                return new AddNumberSequenceTask(difficulty);
            }
        }
        return null;
    }
}
