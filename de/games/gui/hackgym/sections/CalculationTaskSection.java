package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.CalculationTask;
import javax.swing.*;
import java.awt.*;

/**
 * Konkrete {@link TaskSection} zur Darstellung der CalculationTask.
 * Der Benutzer muss den angezeigten mathematischen Ausdruck berechnen
 * und das Ergebnis über ein Eingabefeld eingeben.
 */
public class CalculationTaskSection extends TaskSection {
    private JLabel expressionLabel;
    private JTextField solutionField;

    /**
     * Erstellt eine neue CalculationTaskSection
     * @param task die zugrunde liegende CalculationTask
     */
    public CalculationTaskSection(CalculationTask task) {
        initComponents(task);
    }

    private void initComponents(CalculationTask task) {
        expressionLabel = new JLabel(task.getExpression(), SwingConstants.CENTER);
        applyDefaultStyle(expressionLabel, 48);

        solutionField = new JTextField();
        solutionField.setHorizontalAlignment(JTextField.CENTER);
        applyDefaultStyle(solutionField, 48);
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
