package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.ClickNumberTask;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Konkrete {@link TaskSection} zur Darstellung der ClickNumberTask.
 * Der Benutzer muss alle angezeigten Zahlen bzw. Ausdrücke in der
 * korrekten aufsteigenden Reihenfolge anklicken.
 */
public class ClickNumberTaskSection extends TaskSection {
    private JButton[] numbers;

    /**
     * Erstellt eine neue ClickNumberTaskSection für die übergebene Aufgabe.
     * @param task die zugrunde liegende ClickNumberTask
     */
    public ClickNumberTaskSection(ClickNumberTask task) {
        initComponents(task);
    }

    private void initComponents(ClickNumberTask task) {
        numbers = new JButton[7];
        int[] solutionArr = task.getNumbers();
        AtomicInteger clicks = new AtomicInteger(0);
        StringBuilder solutionEx = new StringBuilder();
        String[] expressions = task.getExpressions();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(expressions[i]);
            JButton curButton = numbers[i];
            curButton.setFont(new Font("Arial", Font.BOLD, 48));
            curButton.setOpaque(true);
            curButton.setBackground(ORANGE_BOX);
            curButton.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(180, 150, 120)));
            int finalI = i;
            curButton.addActionListener(
                    e -> {
                        solutionEx.append(solutionArr[finalI]).append(" ");
                        clicks.incrementAndGet();
                        if(clicks.get() == numbers.length) {
                            setSolution(solutionEx.toString());
                            submitSolution();
                        }
                        curButton.setEnabled(false);
                        curButton.setBackground(new Color(200, 200, 200));
                    }
            );
        }

    }

    /**
     * Baut den Aufgabenbereich auf
     */
    @Override
    public void buildTaskSection() {
        setBlueBackground();
        setLayout(new GridLayout(3, 3, 10, 10));

        for (JButton button: numbers) {
            add(button);
        }

        JPanel emptyLeft = new JPanel();
        JPanel emptyRight = new JPanel();

        emptyLeft.setOpaque(false);
        emptyRight.setOpaque(false);

        add(emptyLeft);
        add(numbers[6]);
        add(emptyRight);
    }
}
