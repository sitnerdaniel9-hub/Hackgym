package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.ColorButtonTask;
import javax.swing.*;
import java.awt.*;

/**
 * Konkrete {@link TaskSection} zur Darstellung der ColorButtonTask.
 * Der Benutzer muss anhand eines angezeigten Textes die korrekte Farbe
 * aus mehreren farblich dargestellten Buttons auswählen.
 */
public class ColorButtonTaskSection extends TaskSection {
    JButton[] colorButtons;
    JLabel colorLabel;

    /**
     * Erstellt eine neue ColorButtonTaskSection für die übergebene Aufgabe.
     * @param task die zugrunde liegende ColorButtonTask
     */
    public ColorButtonTaskSection(ColorButtonTask task) {
        initComponents(task);
    }

    private void initComponents(ColorButtonTask task) {
        colorLabel = new JLabel(task.getColor());
        colorLabel.setFont(new Font("Arial", Font.BOLD, 48));
        colorLabel.setOpaque(true);
        colorLabel.setBackground(Color.WHITE);
        colorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        String[] colors = task.getColors();
        colorButtons = new JButton[5];
        for (int i = 0; i < colorButtons.length; i++) {
            JButton but = new JButton(colors[i]);
            colorButtons[i] = but;
            but.setFont(new Font("Arial", Font.BOLD, 48));
            but.setOpaque(true);
            but.setBackground(task.mapColor(task.chooseButtonColor(i)));
            but.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            but.addActionListener(e -> {
                setSolution(but.getText());
                submitSolution();
            });
        }
    }

    /**
     * Baut den Aufgabenbereich auf
     */
    @Override
    public void buildTaskSection() {
        setLayout(new GridLayout(2, 5, 10, 10));

        JPanel emptyLeft = new JPanel();
        JPanel emptyRight = new JPanel();
        JPanel emptyLeft2 = new JPanel();
        JPanel emptyRight2 = new JPanel();

        emptyLeft.setOpaque(false);
        emptyRight.setOpaque(false);
        emptyLeft2.setOpaque(false);
        emptyRight2.setOpaque(false);
        add(emptyLeft);
        add(emptyLeft2);
        add(colorLabel);
        add(emptyRight);
        add(emptyRight2);

        for (JButton button: colorButtons) {
            add(button);
        }
    }
}
