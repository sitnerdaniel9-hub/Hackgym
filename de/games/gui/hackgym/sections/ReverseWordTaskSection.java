package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.ReverseWordTask;
import javax.swing.*;
import java.awt.*;

/**
 * Konkrete {@link TaskSection} zur Darstellung der ReverseWordTask.
 * Der Benutzer muss einen angezeigten Text korrekt rückwärts eingeben.
 */
public class ReverseWordTaskSection extends TaskSection {

    private JLabel expressionLabel;
    private JTextField solutionField;

    /**
     * Erstellt eine neue ReverseWordTaskSection für die übergebene Aufgabe.
     * @param task die zugrunde liegende ReverseWordTask
     */
    public ReverseWordTaskSection(ReverseWordTask task) {
        initComponents(task);
    }

    private void initComponents(ReverseWordTask task) {
        expressionLabel = new JLabel(task.getReversedSentence(), SwingConstants.CENTER);
        applyDefaultStyle(expressionLabel, 32);

        solutionField = new JTextField();
        solutionField.setHorizontalAlignment(JTextField.CENTER);
        applyDefaultStyle(solutionField, 32);
        solutionField.addActionListener(e -> {
            setSolution(solutionField.getText());
            submitSolution();
        });
    }

    /**
     * Baut den Aufgabenbereich auf und fügt alle UI-Komponenten
     */
    @Override
    public void buildTaskSection() {
        setBlueBackground();
        setLayout(new GridLayout(2,1,10,10));
        add(expressionLabel);
        add(solutionField);
        SwingUtilities.invokeLater(() -> solutionField.requestFocusInWindow());
    }
}
