package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.ArrayFactory;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Diese Klasse gibt den Rahmen der SortTask vor. */
public class SortTask extends Task {
    private final int[] sortedArray;
    private final int[] unsortedArray;

    /**Erstellt eine SortTask*/
    public SortTask() {
        setDifficulty(Difficulty.EASY);
        setPoints(100);
        setType(TaskType.SORT);
        unsortedArray = ArrayFactory.generateArrayWithoutDuplicates(10, 1, 21);
        sortedArray = Arrays.copyOfRange(unsortedArray, 0, unsortedArray.length);
        Arrays.sort(sortedArray);
    }

    /**Erstellt den Ausdruck für die Aufgabe
     * @return der Ausdruck, den der Spieler sieht.*/
    public String getExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j : unsortedArray) {
            stringBuilder.append(j).append(" ");
        }
        return stringBuilder.toString();
    }

    /**Überprüft die Lösung.
     * @param solution die Lösung des Spielers.
     * @return gibt zurück, ob die Lösung richtig oder falsch war.*/
    @Override
    public boolean checkSolution(String solution) {

        Scanner input = new Scanner(solution);
        int[] submittedArray = new int[10];
        int i = 0;
        while (i < submittedArray.length && input.hasNext()) {
            String next = input.next();
            try {
                submittedArray[i] = Integer.parseInt(next);
            } catch (NumberFormatException e) {
                return false;
            }
            i++;
        }

        if (input.hasNext()) {
            return false;
        }

        input.close();
        return Arrays.equals(submittedArray, sortedArray);
    }
}
