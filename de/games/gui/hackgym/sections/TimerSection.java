package de.games.gui.hackgym.sections;

import javax.swing.*;
import java.awt.*;

/**
 * Diese Klasse ist für die Anzeige des Timers in der UI verantwortlich.
 * Sie ist als Singleton umgesetzt.
 */
public class TimerSection extends JPanel{

    private final JLabel timeLabel;
    private static TimerSection timerSection;
    private Timer timer;
    private long startTime;
    private long totalSeconds;

    private TimerSection() {
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial",Font.BOLD, 18));
        add(timeLabel);
    }

    /**
     * Liefert die Singleton-Instanz der TimerSection.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     * @return die Singleton-Instanz der TimerSection
     */
    public static TimerSection getTimerSection() {
        if (timerSection == null) {
            timerSection = new TimerSection();
        }
        return timerSection;
    }

    /**
     * Startet den Spieltimer.
     * Die verbleibende Zeit wird jede Sekunde aktualisiert
     * und in der Oberfläche angezeigt.
     */
    public void startTimer() {
        timer = new Timer(1000, e -> updateTimerDisplay());
        timer.start();
        updateTimerDisplay();
    }

    private void updateTimerDisplay() {
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        long remaining = totalSeconds - elapsed;

        if (remaining <= 0) {
            timeLabel.setText("0:00");
            timer.stop();
            return;
        }

        timeLabel.setText(String.format("%d:%02d", remaining / 60, remaining % 60));
    }

    /**
     * Setzt den Startzeitpunkt des Timers.
     * Der Wert wird in Millisekunden erwartet.
     * @param startTime der Startzeitpunkt des Timers
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Setzt die Gesamtlaufzeit des Timers in Sekunden.
     * @param totalSeconds die Gesamtdauer des Timers in Sekunden
     */
    public void setTotalSeconds(long totalSeconds) {
        this.totalSeconds = totalSeconds;
    }
}
