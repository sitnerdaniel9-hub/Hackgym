package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.ArrayFactory;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.awt.*;
import java.util.Map;
import java.util.Random;

/**
 * Rahmen für die ColorButtonTask
 */
public class ColorButtonTask extends Task {
    private boolean flag;

    private static final Map<String, Color> COLOR_MAP = Map.ofEntries(
            Map.entry("Blau", Color.BLUE),
            Map.entry("Grün", Color.GREEN),
            Map.entry("Rot", Color.RED),
            Map.entry("Lila", new Color(128, 0, 128)),
            Map.entry("Gelb", Color.YELLOW)
    );

    private String color;
    private String[] colors;

    /**
     * Erstellt eine ColorButtonTask
     * @param difficulty die Schwierigkeit der Aufgabe
     */
    public ColorButtonTask(Difficulty difficulty) {
        flag = false;
        setDifficulty(difficulty);
        setPoints(50);
        setType(TaskType.COLOR_BUTTON);
        setUpTask();
    }

    private void setUpTask() {
        colors = ArrayFactory.generateColorArrayWithoutDuplicates();
        Random random = new Random();
        color = colors[random.nextInt(colors.length)];
    }

    /**
     * Liefert die anzuklickende Farbe als String
     * @return die Farbe als String
     */
    public String getColor() {
        return color;
    }

    /**
     * liefert alle anklickbaren Farben als StringArray
     * @return der StringArray aus Farben
     */
    public String[] getColors() {
        return colors;
    }

    /**
     * mappt einen String mit dem Namen der Farbe auf ein Colorobjekt
     * @param color der String der Farbe
     * @return das zugehörige Objekt vom Typ Color
     */
    public Color mapColor(String color) {
        return COLOR_MAP.get(color);
    }

    /**
     * Prüft die Lösung
     * @param solution die Lösung des Spielers
     * @return {@code true}, wenn die Lösung korrekt ist, sonst {@code false}
     */
    @Override
    public boolean checkSolution(String solution) {
        return solution.equals(color);
    }

    /**
     * Entscheidet welcher Button wie eingefärbt wird
     * @param i index im ColorArray
     * @return die Farbe, in die Eingefärbt werden soll
     */
    public String chooseButtonColor(int i) {
        switch (getDifficulty()) {
            case EASY -> {
                return colors[i];
            }
            case MEDIUM -> {
                if (i + 1 == colors.length) {
                    return colors[0];
                }
                return colors[i + 1];
            }
            case HARD -> {

                if (!flag && i == colors.length -1) {
                    return color;
                }
                Random random = new Random();
                i = random.nextInt(colors.length);
                if (colors[i].equals(color)) {
                    flag = true;
                }
                return colors[i];
            }
        }
        return null;
    }




}
