package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.ArrayFactory;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.Scanner;

/**
 * Gibt den Rahmen der RhythmButtonTask vor.*/
public class RyhthmButtonTask extends Task {
    private final int[] pattern;

    /**Erzeugt eine RhythmButtonTask.*/
    public RyhthmButtonTask() {
        setDifficulty(Difficulty.HARD);
        setPoints(100);
        setType(TaskType.RHYTHM_BUTTON);
        pattern = ArrayFactory.generateArray(5, 0, 5);
    }

    /**Gibt den Rhythmus zurück
     * @return Rhythmus als Ganzzahlarray*/
    public int[] getPattern() {
        return pattern;
    }

    /**Überprüft die Lösung der Aufgabe
     * @param solution die Lösung des Spielers
     * @return Gibt zurück, ob die Lösung richtig oder falsch war.*/
    @Override
    public boolean checkSolution(String solution) {
        Scanner input = new Scanner(solution);
        int i = 0;
        while (input.hasNext()) {
            String next = input.next();
            int num;
            try {
                num = Integer.parseInt(next);
            } catch (NumberFormatException e) {
                return false;
            }

            if (i < pattern.length) {
                if (pattern[i] != num) {
                    return false;
                }

            } else {
                return false;
            }
            i++;

        }
        input.close();
        return i == pattern.length;
    }
}
