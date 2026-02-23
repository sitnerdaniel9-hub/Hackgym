package de.games.gui.hackgym.sections;

import de.games.gui.hackgym.GuiController;
import de.games.logic.hackgym.taskManagement.*;
import javax.swing.*;
import java.awt.*;

/**
 * Diese Klasse gibt den Rahmen vor, den alle Aufgaben unabhängig von ihrem Typ erfüllen müssen.
 */
public abstract class TaskSection extends JPanel {
    protected static final Color ORANGE_BOX = new Color(255, 200, 120);
    protected static final Color BLUE_BACKGROUND = new Color(200, 220,255);
    private String solution;

    /**
     * Reicht die aktuell gesetzte Lösung an den {@link GuiController} weiter.
     */
    protected void submitSolution() {
        GuiController.getGuiController().submitSolution(solution);
    }

    /**
     * Setzt den Standard-Hintergrund für Aufgabenbereiche
     * in der vorgesehenen blauen Farbgebung.
     */
    protected void setBlueBackground() {
        setBackground(BLUE_BACKGROUND);
        setOpaque(true);
    }

    /**
     * Setzt die aktuelle Lösung der Aufgabe.
     * @param solution die eingegebene Lösung als String
     */
    protected void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * Wendet das einheitliche Standard-Design auf eine UI-Komponente an.
     * Beinhaltet Schriftart, Hintergrundfarbe und Rahmen.
     * @param c die zu stylende UI-Komponente
     * @param textSize die Schriftgröße
     */
    protected void applyDefaultStyle(JComponent c, int textSize) {
        c.setFont(new Font("Arial", Font.BOLD, textSize));
        c.setOpaque(true);
        c.setBackground(ORANGE_BOX);
        c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    /**
     * Baut den Aufgabenbereich auf.
     * Diese Methode muss von allen konkreten TaskSection-Implementierungen
     * überschrieben werden.
     */
    public abstract void buildTaskSection();

    /**
     * Erzeugt eine passende {@link TaskSection}-Instanz
     * basierend auf dem Typ der übergebenen Aufgabe.
     *
     * @param task die Aufgabe, für die ein Aufgabenbereich erstellt werden soll
     * @return eine konkrete TaskSection-Instanz oder {@code null},
     *         falls kein passender Typ existiert
     */
    public static TaskSection buildTaskSection(Task task) {
        switch (task.getType()) {
            case CALCULATION -> {
                return new CalculationTaskSection((CalculationTask)task);
            }
            case ADD_NUMBER_SEQUENCE -> {
                return new AddNumberSequenceTaskSection((AddNumberSequenceTask)task);
            }
            case RHYTHM_BUTTON -> {
                return new RhythmButtonTaskSection((RyhthmButtonTask)task);
            }
            case PATTERN_ERROR -> {
                return new PatternErrorTaskSection((PatternErrorTask) task);
            }
            case SORT -> {
                return new SortTaskSection((SortTask)task);
            }
            case REVERSE_WORD -> {
                return new ReverseWordTaskSection((ReverseWordTask)task);
            }
            case COLOR_BUTTON -> {
                return new ColorButtonTaskSection((ColorButtonTask)task);
            }
            case CLICK_NUMBER -> {
                return new ClickNumberTaskSection((ClickNumberTask)task);
            }
        }
        return null;
    }
}
