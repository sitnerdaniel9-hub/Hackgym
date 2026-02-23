package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.SortTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Konkrete {@link TaskSection} zur Darstellung einer Sortieraufgabe.
 * Der Benutzer muss vorgegebene Elemente per Mausklick in die
 * korrekte Reihenfolge bringen.
 */
public class SortTaskSection extends TaskSection {

    private JPanel unsortedField;
    private JPanel inputField;
    private JButton submitButton;

    /**
     * Erstellt eine neue SortTaskSection für die übergebene Sortieraufgabe.
     * Initialisiert alle benötigten UI-Komponenten.
     * @param task die zugrunde liegende SortTask
     */
    public SortTaskSection(SortTask task) {
        initComponents(task);
    }

    private void initComponents(SortTask task) {
        unsortedField = new JPanel();
        unsortedField.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        unsortedField.setOpaque(false);
        String[] expressions = task.getExpression().split(" ");
        for (String s: expressions) {
            JLabel label = new JLabel(s, SwingConstants.CENTER);
            applyDefaultStyle(label, 32);
            label.setPreferredSize(new Dimension(60, 60));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    fillNextEmptySlot(inputField, s);
                }
            });
            unsortedField.add(label);

        }
        inputField = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField.setOpaque(false);

        for (int i = 0; i < expressions.length; i++) {
            JLabel field = new JLabel();
            applyDefaultStyle(field, 32);
            field.setHorizontalAlignment(SwingConstants.CENTER);
            field.setPreferredSize(new Dimension(60, 60));
            field.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    field.setText("");
                }
            });
            inputField.add(field);

        }
        submitButton = new JButton("Fertig");
        applyDefaultStyle(submitButton, 32);
        submitButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Component comp: inputField.getComponents()) {
                if (comp instanceof JLabel tf) {
                    sb.append(tf.getText()).append(" ");
                }
            }
            setSolution(sb.toString());
            submitSolution();
        });

    }

    /**
     * Baut den Aufgabenbereich auf und fügt alle UI-Komponenten
     * zum Layout hinzu.
     */
    @Override
    public void buildTaskSection() {
        setBlueBackground();
        setLayout(new GridLayout(3, 1, 10, 10));
        add(unsortedField);
        add(inputField);
        add(submitButton);
    }

    private void fillNextEmptySlot(JPanel panel, String value) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel tf) {
                if (tf.getText().isEmpty()) {
                    tf.setText(value);
                    return;
                }
            }
        }
    }
}
