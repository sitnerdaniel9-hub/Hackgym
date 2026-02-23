package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.taskManagement.RyhthmButtonTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Konkrete {@link TaskSection} zur Darstellung der RhythmButtonTask.
 * Der Benutzer muss ein vorgegebenes Klickmuster nach der Wiedergabe
 * in der korrekten Reihenfolge reproduzieren.
 */
public class RhythmButtonTaskSection extends TaskSection {
    private JButton[] buttons;

    /**
     * Erstellt eine neue RhythmButtonTaskSection für die übergebene Rhythmusaufgabe.
     * @param task die zugrunde liegende Rhythmus-Aufgabe
     */
    public RhythmButtonTaskSection(RyhthmButtonTask task) {
        initComponents(task);
    }

    private void initComponents(RyhthmButtonTask task) {
        buttons = new JButton[5];
        StringBuilder sb = new StringBuilder();
        AtomicInteger clicks = new AtomicInteger(0);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(Integer.toString(i));
            JButton button = buttons[i];
            applyDefaultStyle(button, 48);
            button.addActionListener(e -> {
                sb.append(button.getText()).append(" ");
                clicks.incrementAndGet();
                if (clicks.get() == task.getPattern().length) {
                    setSolution(sb.toString());
                    submitSolution();
                }
            });
        }
        startRhythm(buttons, task);

    }

    private void startRhythm(JButton[] buttons, RyhthmButtonTask task) {

        int[] pattern = task.getPattern();

        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }

        Timer startDelay = new Timer(3000, e -> playPattern(pattern, buttons));
        startDelay.setRepeats(false);
        startDelay.start();
    }

    /**
     * Spielt das Rhythmus-Muster ab.
     */
    private void playPattern(int[] pattern, JButton[] buttons) {

        AtomicInteger index = new AtomicInteger(0);

        Timer timer = new Timer(1000, null);  // 1 Sekunde pro Button

        timer.addActionListener((ActionEvent e) -> {
            int i = index.getAndIncrement();

            if (i >= pattern.length) {
                timer.stop();
                for (JButton b : buttons) b.setEnabled(true);
                return;
            }

            JButton btn = buttons[pattern[i]];
            Color original = btn.getBackground();

            btn.setBackground(Color.RED);

            Timer restore = new Timer(300, ev -> btn.setBackground(original));
            restore.setRepeats(false);
            restore.start();
        });

        timer.start();
    }

    /**
     * Baut den Aufgabenbereich auf und fügt alle Rhythmus-Buttons
     * in das Layout ein.
     */
    @Override
    public void buildTaskSection() {
        setLayout(new GridLayout(1, 5, 10, 10));
        for (JButton button: buttons) {
            add(button);
        }

    }
}
