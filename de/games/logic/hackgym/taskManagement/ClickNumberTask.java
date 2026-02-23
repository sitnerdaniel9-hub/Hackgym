package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.ArrayFactory;
import de.games.logic.hackgym.ExpressionFactory;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Rahmen für die ClickNumberTask
 */
public class ClickNumberTask extends Task {
    private static final int LENGTH = 7;
    private int[] numbers;
    private String[] expressions;

    /**
     * Erstellt eine neue ClickNumberTask mit dem angegebenen Schwierigkeitsgrad.
     * @param difficulty der Schwierigkeitsgrad der Aufgabe
     */
    public ClickNumberTask(Difficulty difficulty) {
        setDifficulty(difficulty);
        setType(TaskType.CLICK_NUMBER);
        setPoints(100);
        setUpTask(difficulty);
    }

    private void setUpTask(Difficulty difficulty) {
        numbers = ArrayFactory.generateArrayWithoutDuplicates(LENGTH, 1, 21);
        expressions = new String[LENGTH];
        switch (difficulty) {
            case EASY -> {
                for (int i = 0; i < expressions.length; i++) {
                    expressions[i] = Integer.toString(numbers[i]);
                }
            }

            case MEDIUM -> {
                for (int i = 0; i < expressions.length; i++) {
                    expressions[i] = ExpressionFactory.generateExpression(numbers[i], Difficulty.EASY);
                }
            }
        }
    }

    /**
     * Prüft, ob die übergebene Lösung korrekt ist.
     * @param solution die vom Benutzer eingegebene Lösung als String
     * @return {@code true}, wenn die Lösung korrekt ist, sonst {@code false}
     */
    @Override
    public boolean checkSolution(String solution) {
        Arrays.sort(numbers);
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
            if (i < numbers.length) {
                if (numbers[i] != num) {
                    return false;
                }

            } else {
                return false;
            }
            i++;

        }
        input.close();
        return i == numbers.length;

    }

    /**
     * Liefert die darzustellenden Ausdrücke der Aufgabe.
     * Diese können je nach Schwierigkeitsgrad reine Zahlen oder mathematische
     * Ausdrücke sein.
     * @return ein Array von Strings mit den Ausdrücken der Aufgabe
     */
    public String[] getExpressions() {
        return expressions;
    }

    /**
     * Liefert die zugrunde liegenden Zahlen der Aufgabe.
     * @return ein Array von Ganzzahlen der Aufgabe
     */
    public int[] getNumbers() {
        return numbers;
    }
}
