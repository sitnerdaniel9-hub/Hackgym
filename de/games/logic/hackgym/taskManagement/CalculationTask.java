package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.ExpressionFactory;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.Random;

/**
 * Rahmen für die CalculationTask
 */
public class CalculationTask extends Task {
    private String expression;
    private int solution;

    /**
     * Erstellt eine neue CalculationTask mit dem angegebenen Schwierigkeitsgrad.
     * @param difficulty der Schwierigkeitsgrad der Aufgabe
     */
    public CalculationTask(Difficulty difficulty) {
        setDifficulty(difficulty);
        setType(TaskType.CALCULATION);
        setPoints(100);
        setUpTask(difficulty);

    }


    private void setUpTask(Difficulty difficulty) {
        Random rand = new Random();
        switch (difficulty) {
            case EASY -> {
                solution = rand.nextInt(20) + 1;
                expression = ExpressionFactory.generateExpression(solution, difficulty);
            }
            case MEDIUM, HARD -> {
                solution = rand.nextInt(100) + 1;
                expression = ExpressionFactory.generateExpression(solution, difficulty);
            }
        }
    }

    /**
     * Liefert den mathematischen Ausdruck, der vom Benutzer berechnet werden soll.
     * @return der mathematische Ausdruck als String
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Prüft, ob die übergebene Lösung korrekt ist.
     * @param solution die vom Benutzer eingegebene Lösung als String
     * @return {@code true}, wenn die Lösung korrekt ist, sonst {@code false}
     */
    @Override
    public boolean checkSolution(String solution) {
        try {
            int value = Integer.parseInt(solution);
            return value == this.solution;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
