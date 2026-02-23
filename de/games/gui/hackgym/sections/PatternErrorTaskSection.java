package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.PatternErrorTask;
import javax.swing.*;
import java.awt.*;

/**
 * Konkrete {@link TaskSection} zur Darstellung der PatternErrorTask.
 * Der Benutzer muss einen Fehler in einem dargestellten Muster erkennen
 * und diesen über ein Eingabefeld korrigieren.
 */
public class PatternErrorTaskSection extends TaskSection {
    private JLabel expressionLabel;
    private JTextField solutionField;

    /**
     * Erstellt eine neue PatternErrorTaskSection für die übergebene Aufgabe.
     * @param task die zugrunde liegende PatternErrorTask
     */
    public PatternErrorTaskSection(PatternErrorTask task) {
        initComponents(task);
    }

    private void initComponents(PatternErrorTask task) {
        expressionLabel = new JLabel(task.getPattern(), SwingConstants.CENTER);
        applyDefaultStyle(expressionLabel, 20);

        solutionField = new JTextField();
        solutionField.setHorizontalAlignment(JTextField.CENTER);
        applyDefaultStyle(solutionField, 20);
        solutionField.addActionListener(e -> {
            setSolution(solutionField.getText());
            submitSolution();
        });
    }


    /**
     * Baut den Aufgabenbereich auf
     */
    @Override
    public void buildTaskSection() {

        setBlueBackground();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ausdruck links
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        add(expressionLabel, gbc);

        // Eingabefeld rechts
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        add(solutionField, gbc);
        SwingUtilities.invokeLater(() -> solutionField.requestFocusInWindow());
    }
}
