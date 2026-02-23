package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.AddNumberSequenceTask;
import javax.swing.*;
import java.awt.*;

/**
 * Konkrete {@link TaskSection} zur Darstellung der AddNumberSequenceTask.
 * Der Benutzer muss eine angezeigte Zahlenfolge analysieren und
 * das fehlende Element korrekt ergänzen.
 */
public class AddNumberSequenceTaskSection extends TaskSection {
    private JPanel expressionPanel;
    private JTextField solutionField;

    /**
     * Erstellt eine neue AddNumberSequenceTaskSection.
     * @param task die zugrunde liegende AddNumberSequenceTask
     */
    public AddNumberSequenceTaskSection(AddNumberSequenceTask task) {
        initComponents(task);
    }

    private void initComponents(AddNumberSequenceTask task) {
        expressionPanel = new JPanel();
        expressionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        expressionPanel.setOpaque(false);
        String[] numbers = task.getExpression().split(" ");
        for (String s: numbers) {
            JLabel label = new JLabel(s, SwingConstants.CENTER);
            applyDefaultStyle(label, 24);
            label.setPreferredSize(new Dimension(60, 60));
            expressionPanel.add(label);
        }
        solutionField = new JTextField();
        solutionField.setHorizontalAlignment(JTextField.CENTER);
        applyDefaultStyle(solutionField, 24);
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
        add(expressionPanel, gbc);

        // Eingabefeld rechts
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(solutionField, gbc);

        // Quadratische Größe
        solutionField.setPreferredSize(new Dimension(60, 60));
        SwingUtilities.invokeLater(() -> solutionField.requestFocusInWindow());
    }
}
